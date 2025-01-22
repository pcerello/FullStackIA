import React from "react";
import "./Game.scss";
import Header from "../../components/Header/Header";
import Footer from "../../components/Footer/Footer";
import ApiService from "../../services/ApiService";
import accusationImage from "../../assets/accusation.png";
import { useNavigate } from "react-router-dom";
import { useLocation } from "react-router-dom";
import { useState } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faClockRotateLeft } from "@fortawesome/free-solid-svg-icons";

function Game() {

  const navigate = useNavigate();
  const [temoin, setTemoin] = useState("");
  const [coupable, setCoupable] = useState("");
  const [isModal, setIsModal] = useState(false);
  const [isLoading, setIsLoading] = useState(false);
  const location = useLocation();
  const data = location.state;

  const handleTemoinChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setTemoin(event.target.value);
  };

  const handleCoupableChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setCoupable(event.target.value);
  };

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
    // navigate("/endgame");
  };

  const handleCoupableSubmit = (event: React.FormEvent) => {
    event.preventDefault();
    // Logic to start the game with the entered theme
    console.log("J'accuse :", coupable);
    // Redirect to the game page using React Router's navigate
    fetchDataCoupable(coupable);
    // navigate("/endgame");
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
              {data?.scenario}
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
