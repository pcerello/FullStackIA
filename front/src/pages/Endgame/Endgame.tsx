import React from "react";
import { useLocation, useNavigate } from "react-router-dom";
import "./Endgame.scss";
import Header from "../../components/Header/Header";
import Footer from "../../components/Footer/Footer";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faAddressCard } from "@fortawesome/free-solid-svg-icons";

function Endgame() {
  const navigate = useNavigate();
  const location = useLocation();
  const data = location.state;

  const convertTime = (time: number) => {
    const minutes = Math.floor(time / 60);
    const seconds = time % 60;
    return `${minutes} minutes et ${seconds} secondes`;
  };

  function handleRetour() {
    navigate("/");
  }

  if (!data) {
    return (
      <div className="Endgame">
        <Header />
        <div className="Body-endgame">
          <h1>Erreur : aucune donnée trouvée</h1>
        </div>
        <Footer />
      </div>
    );
  }

  return (
    <div className="Endgame">
      <Header />
      <div className="Body-endgame">
        <div className="end-game__container">
          <div className="end-game__trophy">
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 576 512" fill="currentColor">
              <path d="M64 32C28.7 32 0 60.7 0 96L0 416c0 35.3 28.7 64 64 64l448 0c35.3 0 64-28.7 64-64l0-320c0-35.3-28.7-64-64-64L64 32zm80 256l64 0c44.2 0 80 35.8 80 80c0 8.8-7.2 16-16 16L80 384c-8.8 0-16-7.2-16-16c0-44.2 35.8-80 80-80zm-32-96a64 64 0 1 1 128 0 64 64 0 1 1 -128 0zm256-32l128 0c8.8 0 16 7.2 16 16s-7.2 16-16 16l-128 0c-8.8 0-16-7.2-16-16s7.2-16 16-16zm0 64l128 0c8.8 0 16 7.2 16 16s-7.2 16-16 16l-128 0c-8.8 0-16-7.2-16-16s7.2-16 16-16zm0 64l128 0c8.8 0 16 7.2 16 16s-7.2 16-16 16l-128 0c-8.8 0-16-7.2-16-16s7.2-16 16-16z" />
            </svg>
          </div>
          <h1>Verdict !</h1>
          <p className="end-game__time">Temps : {convertTime(data.timer)} </p>
          <div className="end-game__description">
            <p>
              {data.description}
            </p>
          </div>
          <button className="end-game__button" onClick={() => handleRetour()}>Accueil</button>
        </div>
      </div>
      <Footer />
    </div>
  );
}

export default Endgame;
