import React, { useState, useEffect } from "react";
import "./Game.scss";
import Header from "../../components/Header/Header";
import Footer from "../../components/Footer/Footer";
import ApiService from "../../services/ApiService";
import accusationImage from "../../assets/accusation.png";
import { useNavigate, useParams } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faClockRotateLeft } from "@fortawesome/free-solid-svg-icons";

function Game() {

  const navigate = useNavigate();
  const [temoin, setTemoin] = useState("");
  const [coupable, setCoupable] = useState("");
  const [isModal, setIsModal] = useState(false);
  const [isLoading, setIsLoading] = useState(false);
  const [scenario, setScenario] = useState("");
  const [temoignages, setTemoignages] = useState("");
  const { id } = useParams();

  const handleTemoinChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setTemoin(event.target.value);
  };

  const handleCoupableChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setCoupable(event.target.value);
  };

  async function fetchInfoScenario() {
    try {
      console.log("Fetching Info scenario...");
      const scenario = await ApiService.get(`scenario/${id}`);
      setScenario(scenario?.description || "Scénario indisponible.");
      
      const temoignages = await ApiService.get(`temoignages/${id}`);
      // setTemoignages(temoignages?.description || "Témoignages indisponibles.");
      setTemoignages(temoignages.length || "Témoignages indisponibles.");
      console.log(temoignages.length);
    } catch (error) {
      console.error("Erreur lors de l'appel API :", error);
      setScenario("Erreur lors de la récupération du scénario.");
    }
  }
  
  useEffect(() => {
    fetchInfoScenario();
  }, [id]);

  async function fetchDataTemoin(request: String) {
    try {
        setIsModal(true);
        console.log("Waiting ...");
        const data = await ApiService.post("genererTemoignages", request);
        console.log(data);
    } catch (error) {
        console.error("Erreur lors de l'appel API :", error);
    } finally {
      setIsModal(false);
    }
  }

  async function fetchDataCoupable(request: String) {
    try {
        setIsLoading(true);
        console.log("Waiting ...");
        const data = await ApiService.post("evaluationReponseUtilisateur", request);
        console.log(data);
        navigate("/endgame", { state: data });
    } catch (error) {
        console.error("Erreur lors de l'appel API :", error);
    } finally {
      setIsLoading(false);
    }
  }

  const handleTemoinSubmit = (event: React.FormEvent) => {
    event.preventDefault();
    // Logic to start the game with the entered theme
    console.log("Interrogatoire de :", temoin);
    // Redirect to the game page using React Router's navigate
    fetchDataTemoin(temoin);
  };

  const handleCoupableSubmit = (event: React.FormEvent) => {
    event.preventDefault();
    // Logic to start the game with the entered theme
    console.log("J'accuse :", coupable);
    // Redirect to the game page using React Router's navigate
    fetchDataCoupable(coupable);
  };

  return (
    <div className="Game">
      <Header buttonAbend={true}/>
      <div className="Body-game">

        <div className="Top">
        <div className="Description">
          <h1>Scénario du jeu</h1>

          <div className="ombrage">
            <p className="texte">
              {scenario || "Chargement du scénario..."}
              <br />
              &nbsp;
            </p>
          </div>
        </div>
        <div className="Testimonials">
          <h2>Section interrogatoire</h2>
          <form className="examination" onSubmit={handleTemoinSubmit}>
            <input  type="text" 
                    placeholder="Qui voulez-vous interroger ?"
                    value={temoin}
                    onChange={handleTemoinChange} />
            <button type="submit">Interroger</button>
          </form>
          <div className="TestimonialsList">
            {temoignages || "Chargement du scénario..."}
            <button>
            <FontAwesomeIcon icon={faClockRotateLeft} />
              Témoignages</button>
          </div>
        </div>
      </div>
      <div className="Bottom">

        <div className="Accusation">
          <img src={accusationImage} alt="accusation" className="accusation-image" />
          <h2>Section accusation </h2>
          <form className="accusationForm"  onSubmit={handleCoupableSubmit}>
            <input type="text" 
                   value={coupable}
                   onChange={handleCoupableChange}
                   placeholder="Qui est le coupable ?" />
              <button type="submit" disabled={isLoading}>
                {isLoading ? (
                  <span className="loader">Attendez... longtemps... très longtemps...</span>
                ) : (
                  "Accuser"
                )}
              </button>
          </form>
        </div>
        </div>
      </div>
      <Footer />
    </div>
  );
}

export default Game;
