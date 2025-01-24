import React, { useState } from "react";
import "./Home.scss";
import Header from "../../components/Header/Header";
import Footer from "../../components/Footer/Footer";
import ApiService from "../../services/ApiService";
import { useNavigate } from "react-router-dom";
import enqueteImage from "../../assets/enquete.png";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faClockRotateLeft } from "@fortawesome/free-solid-svg-icons";

function Home() {
  const navigate = useNavigate();
  const [theme, setTheme] = useState("");
  const [isLoading, setIsLoading] = useState(false);
  const [Historiques, setHistoriques] = useState<{ id: 0, description: "" }[]>([]);
  const [isModalHistorique, setIsModalHistorique] = useState(false);

  const handleThemeChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setTheme(event.target.value);
  };

  async function fetchData(question: String) {
    try {
        setIsLoading(true);
        const data = await ApiService.post("genererScenario", question);
        console.log(data);
        navigate("/game/" + data.id );
    } catch (error) {
        console.error("Erreur lors de l'appel API :", error);
    } finally {
      setIsLoading(false);
    }
  }

  const handleSubmit = (event: React.FormEvent) => {
    event.preventDefault();
    fetchData(theme);
  };

  async function fetchHistorique() {
    try {
      console.log("Fetching Temoignages...");
      const temoignages = await ApiService.get(`historiqueScenarios`);
      setHistoriques(temoignages);
      console.log(temoignages);


    } catch (error) {
      console.error("Erreur lors de l'appel API :", error);
    }
  }

  const handleListHistoriques = () => {
    console.log("Liste des 5 dernière histoire");
    setIsModalHistorique(true);
    fetchHistorique();
  };

  return (
      <div className="Home">
        <Header />
        <div className="Body-home">
          {/* <img src={enqueteImage} alt="enquete" className="enquete-image" /> */}
          <div className="form-overlay">
            <h2>Saisissez le thème de votre enquête</h2>
            <form onSubmit={handleSubmit}>
              <input
                type="text"
                value={theme}
                onChange={handleThemeChange}
                placeholder="Ex: Meurtre mystérieux"
                disabled={isLoading} // Désactiver l'input pendant le chargement
                />
                <button type="submit" disabled={isLoading}>
                  {isLoading ? (
                    <span className="loader">Attendez... longtemps... très longtemps...</span>
                  ) : (
                    "Lancer la partie"
                  )}
                </button>
            </form>
          </div>
          <div className="TestimonialsList">
            {/* {historiques.length > 0? historiques.map((historique) => (
              <button key={historique.id} onClick={() => setTemoignage(historique)}>
                Témoignage {historique.id}
              </button>
            )) : "Chargement des témoignages..."} */}
            
            <button onClick={() => handleListHistoriques()}>
              <FontAwesomeIcon icon={faClockRotateLeft} />
              Témoignages
            </button>
          </div>
        </div>
        <Footer />
      </div>
  );
}

export default Home;
