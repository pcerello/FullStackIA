import React, { useState, useEffect } from "react";
import "./Home.scss";
import Header from "../../components/Header/Header";
import Footer from "../../components/Footer/Footer";
import ApiService from "../../services/ApiService";
import { useNavigate } from "react-router-dom";
import enqueteImage from "../../assets/enquete.png";

function Home() {
  const navigate = useNavigate();
  const [theme, setTheme] = useState("");
  const [isLoading, setIsLoading] = useState(false);
  const [history, setHistory] = useState("");

  async function fetchHistory() {
    try {
      console.log("Fetching Historique...");
      const history = await ApiService.get('historiqueScenarios');
      // setTemoignages(temoignages?.description || "Témoignages indisponibles.");
      setHistory(history.length || "Pas d'historique d'indisponibles.");
      console.log(history.length);
    } catch (error) {
      console.error("Erreur lors de l'appel API :", error);
      setHistory("Erreur lors de la récupération du scénario.");
    }
  }
  
  useEffect(() => {
    fetchHistory();
  }, []);

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
      </div>
      <Footer />
    </div>
  );
}

export default Home;
