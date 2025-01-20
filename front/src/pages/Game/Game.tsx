import React from "react";
import "./Game.scss";
import enqueteImage from "../../assets/enquete.png";
import Header from "../../components/Header/Header.tsx";
import Footer from "../../components/Footer/Footer.tsx";

function Game() {
  <div className="App">
    <Header />
    <div className="Body">
      <img src={enqueteImage} alt="logo" />
    </div>
    <Footer />
  </div>;
}

export default Game;
