import React from "react";
import "./Game.scss";
import Header from "../../components/Header/Header.tsx";
import Footer from "../../components/Footer/Footer.tsx";
import  accusationImage  from "../../assets/accusation.png";

function Game() {
  return (
    <div className="Game">
      <Header />
      <div className="Body-game">
        <div className="Description">
          <h1>Scénario du jeu</h1>
          <p>Vous êtes un détective privé, vous avez été engagé pour résoudre un meurtre mystérieux.</p>
        </div>
        <div className="Testimonials">
          <form className="examination">
            <input type="text" placeholder="Qui voulez-vous interroger ?" />
            <button type="submit">Interroger</button>
          </form>
          <div className="TestimonialsList">
            <button>Témoignages</button>
          </div>
        </div>
        <img src={accusationImage} alt="accusation" className="accusation-image" />
        <div className="Accusation">
          <h2>Section accusation </h2>
          <form className="accusation">
            <input type="text" placeholder="Qui est le coupable ?" />
            <button type="submit">Accuser</button>
          </form>
        </div>
      </div>
      <Footer />
    </div>
  );
}

export default Game;
