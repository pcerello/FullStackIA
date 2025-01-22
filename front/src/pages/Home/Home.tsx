import React, { useState } from "react";
import "./Home.scss";
import Header from "../../components/Header/Header";
import Footer from "../../components/Footer/Footer";
import { useNavigate } from "react-router-dom";
import enqueteImage from "../../assets/enquete.png";

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
    // Redirect to the game page using React Router's navigate
    navigate("/game");
  };

  return (
    <div className="Home">
      <Header />
      <div className="Body-home">
        <img src={enqueteImage} alt="enquete" className="enquete-image" />
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
