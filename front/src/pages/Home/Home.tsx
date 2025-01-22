import React, { useState } from "react";
import "./Home.scss";
import Header from "../../components/Header/Header.tsx";
import Footer from "../../components/Footer/Footer.tsx";
import { useNavigate } from "react-router-dom";

function Home() {
  const navigate = useNavigate();
  const [theme, setTheme] = useState("");

  const handleThemeChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setTheme(event.target.value);
  };

  const handleSubmit = (event: React.FormEvent) => {
    event.preventDefault();
    // Logic to start the game with the entered theme
    console.log("Thème de l'enquête :", theme);

    // Appeler le serveur pour recuperer le scenario
    // Mettre un loader
    // Attendre le retour du Json du serveur
    // Rediriger sur la page GAME en Post avec le json
    navigate("/game");

  };

  return (
    <div className="Home">
      <Header />
      <div className="Body-home">
        
        <div className="form-overlay">
          <h2>Saisissez le thème de votre enquête</h2>
          <form onSubmit={handleSubmit}>
            <input
              type="text"
              value={theme}
              onChange={handleThemeChange}
              placeholder="Ex: Meurtre mystérieux"
            />
            <button type="submit">Lancer la partie</button>
          </form>
        </div>
      </div>
      <Footer />
    </div>
  );
}

export default Home;
