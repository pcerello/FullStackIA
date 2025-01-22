import React from "react";
import "./Game.scss";
import Header from "../../components/Header/Header";
import Footer from "../../components/Footer/Footer";
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
  const location = useLocation();
  const data = location.state;

  const handleTemoinChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setTemoin(event.target.value);
  };

  const handleCoupableChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setCoupable(event.target.value);
  };

  const handleTemoinSubmit = (event: React.FormEvent) => {
    event.preventDefault();
    // Logic to start the game with the entered theme
    console.log("Appel au temoi :", temoin);
    // Redirect to the game page using React Router's navigate
    // navigate("/endgame");
  };

  const handleCoupableSubmit = (event: React.FormEvent) => {
    event.preventDefault();
    // Logic to start the game with the entered theme
    console.log("Le coupable est :", coupable);
    // Redirect to the game page using React Router's navigate
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
            <button type="submit">Accuser</button>
          </form>
        </div>
        </div>
      </div>
      <Footer />
    </div>
  );
}

export default Game;
