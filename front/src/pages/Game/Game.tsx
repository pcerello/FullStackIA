import React from "react";
import "./Game.scss";
import Header from "../../components/Header/Header";
import Footer from "../../components/Footer/Footer";
import ApiService from "../../services/ApiService";
import accusationImage from "../../assets/accusation.png";
import { useNavigate, useLocation, useParams } from "react-router-dom";
import { useState } from "react";
import { useEffect } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faClockRotateLeft } from "@fortawesome/free-solid-svg-icons";
import Modal from "../../components/Modal/Modal";

function Game() {
  const navigate = useNavigate();
  const [temoin, setTemoin] = useState("");
  const [temoignage, setTemoignage] = useState<{ id: 0, description: "" }>({ id: 0, description: "" });
  const [temoignages, setTemoignages] = useState<{ id: 0, description: "" }[]>([]);
  const [coupable, setCoupable] = useState("");
  const [isModalTemoignage, setIsModalTemoignage] = useState(false);
  const [isLoadingModal, setIsLoadingModal] = useState(false);
  const [isModalListTemoignages, setIsModalListTemoignages] = useState(false);
  const [isLoading, setIsLoading] = useState(false);
  const [scenario, setScenario] = useState("");
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

      
      console.log(temoignages);
    } catch (error) {
      console.error("Erreur lors de l'appel API :", error);
      setScenario("Erreur lors de la récupération du scénario.");
    }
  }

  async function fetchTemoignages() {
    try {
      console.log("Fetching Temoignages...");
      const temoignages = await ApiService.get(`temoignages/${id}`);
      setTemoignages(temoignages);
      console.log(temoignages);


    } catch (error) {
      console.error("Erreur lors de l'appel API :", error);
    }
  }

  useEffect(() => {
    fetchInfoScenario();
  }, [id]);

  async function fetchDataTemoin(request: String) {
    try {
      setIsLoadingModal(true);
      setIsModalTemoignage(true);
      console.log("Waiting ...");
      const unTemoignage = await ApiService.post("genererTemoignages", request);
      setTemoignage(unTemoignage);
      console.log("temoignage : ", unTemoignage);
    } catch (error) {
      console.error("Erreur lors de l'appel API :", error);
    } finally {
      console.log("Done !");
      setIsLoadingModal(false);
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
    // navigate("/endgame");
  };

  const handleListTemoignages = () => {
    console.log("Liste des témoignages");
    setIsModalListTemoignages(true);
    fetchTemoignages();
  };

  return (
    <div className="Game">
      <Header buttonAbend={true} />
      <div className="Body-game">
        <Modal open={isModalListTemoignages} content={temoignages} title={"Historique des témoignages"} setIsModal={setIsModalListTemoignages} />
        <Modal open={isModalTemoignage} content={temoignage} title={"Témoignage"} setIsModal={setIsModalTemoignage} />
        <div className="Top">
          <div className="Description">
            <h1>Scénario du jeu</h1>

            <div className="ombrage">
              <p className="texte">
                <p>&nbsp;</p>
                {scenario || "Chargement du scénario..."}
                <p>&nbsp;</p>
              </p>
            </div>
          </div>
          <div className="Testimonials">
            <h2>Section interrogatoire</h2>
            <form className="examination" onSubmit={handleTemoinSubmit}>
              <input type="text" placeholder="Qui voulez-vous interroger ?" value={temoin} onChange={handleTemoinChange} />
              <button type="submit">Interroger</button>
            </form>
            <div className="TestimonialsList">
              {/* {temoignages.length > 0? temoignages.map((temoignage) => (
                <button key={temoignage.id} onClick={() => setTemoignage(temoignage)}>
                  Témoignage {temoignage.id}
                </button>
              )) : "Chargement des témoignages..."} */}
              
              <button onClick={() => handleListTemoignages()}>
                <FontAwesomeIcon icon={faClockRotateLeft} />
                Témoignages
              </button>
            </div>
          </div>
        </div>
        <div className="Bottom">
          <div className="Accusation">
            <img src={accusationImage} alt="accusation" className="accusation-image" />
            <h2>Section accusation </h2>
            <form className="accusationForm" onSubmit={handleCoupableSubmit}>
              <input type="text" value={coupable} onChange={handleCoupableChange} placeholder="Qui est le coupable ?" />
              <button type="submit" disabled={isLoading}>
                {isLoading ? <span className="loader">Attendez... longtemps... très longtemps...</span> : "Accuser"}
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
