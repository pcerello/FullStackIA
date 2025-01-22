import React from "react";
import "./Endgame.scss";
import enqueteImage from "../../assets/enquete.png";
import Header from "../../components/Header/Header";
import Footer from "../../components/Footer/Footer";

function Endgame() {
  return (
    <div className="Endgame">
      <Header />
      <div className="Body-endgame">
        <img src={enqueteImage} alt="logo" />
      </div>
      <Footer />
    </div>
  );
}

export default Endgame;
