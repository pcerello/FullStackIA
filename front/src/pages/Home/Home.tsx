import React, { useState } from "react";
import "./Home.scss";
import Header from "../../components/Header/Header";
import Footer from "../../components/Footer/Footer";
import ApiService from "../../services/ApiService";
import { useNavigate } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faClockRotateLeft, faPlay } from "@fortawesome/free-solid-svg-icons";
import ModalHome from "../../components/ModalHome/ModalHome";

const Home: React.FC = () => {
  const navigate = useNavigate();
  const [theme, setTheme] = useState<string>("");
  const [isLoading, setIsLoading] = useState<boolean>(false);
  const [historiques, setHistoriques] = useState<{ id: number; description: string }[]>([]);
  const [isModalHistorique, setIsModalHistorique] = useState<boolean>(false);

  const handleThemeChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setTheme(event.target.value);
  };

  const fetchData = async (question: string) => {
    try {
      setIsLoading(true);
      const data = await ApiService.post("genererScenario", question);
      console.log(data);
      navigate("/game/" + data.id);
    } catch (error) {
      console.error("Erreur lors de l'appel API :", error);
    } finally {
      setIsLoading(false);
    }
  };

  const handleSubmit = (event: React.FormEvent) => {
    event.preventDefault();
    fetchData(theme);
  };

  const fetchHistorique = async () => {
    try {
      console.log("Fetching Historique...");
      const temoignages = await ApiService.get("historiqueScenarios");
      setHistoriques(temoignages);
    } catch (error) {
      console.error("Erreur lors de l'appel API :", error);
    }
  };

  const handleListHistoriques = () => {
    setIsModalHistorique(true);
    fetchHistorique();
  };

  const closeModal = () => {
    setIsModalHistorique(false);
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
              disabled={isLoading}
            />
            <button type="submit" disabled={isLoading}>
              {isLoading ? (
                <span className="loader">Attendez... longtemps... très longtemps...</span>
              ) : (
                <>
                <FontAwesomeIcon icon={faPlay} /> Lancer la partie
                </>
              )}
            </button>
          </form>
          <button onClick={handleListHistoriques}>
            <FontAwesomeIcon icon={faClockRotateLeft} />
            Historique
          </button>
        </div>
      </div>
      <Footer />
      <ModalHome isOpen={isModalHistorique} onClose={closeModal} data={historiques} />
    </div>
  );
};

export default Home;
