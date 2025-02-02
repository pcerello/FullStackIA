import "./Game.scss";
import React, { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faClockRotateLeft } from "@fortawesome/free-solid-svg-icons";
import Header from "../../components/Header/Header";
import Footer from "../../components/Footer/Footer";
import ApiService from "../../services/ApiService";
import Modal from "../../components/Modal/Modal";

function Game() {
  const navigate = useNavigate();
  const { id } = useParams();
  const [temoin, setTemoin] = useState("");
  const [temoignage, setTemoignage] = useState<{ id: number; description: string }>({ id: 0, description: "" });
  const [temoignages, setTemoignages] = useState<{ id: number; description: string }[]>([]);
  const [coupable, setCoupable] = useState("");
  const [isModalTemoignage, setIsModalTemoignage] = useState(false);
  const [isModalListTemoignages, setIsModalListTemoignages] = useState(false);
  const [isLoadingGuilty, setIsLoadingGuilty] = useState(false);
  const [isLoadingTemoin, setIsLoadingTemoin] = useState(false);
  const [scenario, setScenario] = useState("");

  const [elapsedTime, setElapsedTime] = useState(0);
  const [isGameOver, setIsGameOver] = useState(false);

  useEffect(() => {
    if (isGameOver) return;

    const timer = setInterval(() => {
      setElapsedTime((prevElapsedTime) => prevElapsedTime + 1);
    }, 1000);

    return () => clearInterval(timer);
  }, [elapsedTime]);

  // RECUPERATION DONNEE HISTOIRE
  async function fetchInfoScenario() {
    try {
      const scenario = await ApiService.get(`scenario/${id}`);
      setScenario(scenario?.description || "Scénario indisponible.");
    } catch (error) {
      console.error("Erreur lors de l'appel API :", error);
      setScenario("Erreur lors de la récupération du scénario.");
    }
  }

  // RECUPERATION ID HISTOIRE
  useEffect(() => {
    fetchInfoScenario();
  }, [id]);

  useEffect(() => {

  }, [temoignage]);

  // ACTION TEMOIN
  const handleTemoinSubmit = (event: React.FormEvent) => {
    event.preventDefault();
    fetchDataTemoin(temoin);
  };

  // DATA TEMOIN
  async function fetchDataTemoin(request: string) {
    try {
      setIsLoadingTemoin(true);
      const unTemoignage = await ApiService.post(`genererTemoignages/` + id, request); // Id ajouté : `genererTemoignages/${id}`
      setIsModalTemoignage(true);
      setTemoignage(unTemoignage);
    } catch (error) {
      console.error("Erreur lors de l'appel API :", error);
    } finally {
      setIsLoadingTemoin(false);
    }
  }

  // ACTION COUPABLE
  const handleCoupableSubmit = (event: React.FormEvent) => {
    event.preventDefault();
    fetchDataCoupable(coupable);
  };

  // DATA COUPABLE
  async function fetchDataCoupable(request: string) {
    try {
      setIsLoadingGuilty(true);
      const data = await ApiService.post(`evaluationReponseUtilisateur/` + id + `?timer=${elapsedTime}`, request); // Id ajouté : `evaluationReponseUtilisateur/${id}`
      setIsGameOver(true);
      navigate("/endgame", { state: data });
    } catch (error) {
      console.error("Erreur lors de l'appel API :", error);
    } finally {
      setIsLoadingGuilty(false);
    }
  }

  // DATA HISTORIQUE TEMOINS
  async function fetchTemoignages() {
    try {

      const temoignages = await ApiService.get(`scenario/${id}/ListeTemoignages`);
      setTemoignages(temoignages);
    } catch (error) {
      console.error("Erreur lors de l'appel API :", error);
    }
  }

  // REVOIR LA LISTE DE TEMOIGNAGE
  const handleListTemoignages = () => {

    setIsModalListTemoignages(true);
    fetchTemoignages();
  };

  return (
    <div className="Game">
      <Header buttonAbend={true} />
      <div className="Body-game">
        <Modal open={isModalListTemoignages} content={temoignages} title="Historique des témoignages" setIsModal={setIsModalListTemoignages} />
        <Modal open={isModalTemoignage} content={temoignage} title="Témoignage" setIsModal={setIsModalTemoignage} />
        <div className="Top">
          <div className="Description">
            <h1>Scénario du jeu</h1>
            <div className="bloc_scenario">
              <div className="texte">
                <p>&nbsp;</p>
                {scenario || "Chargement du scénario..."}
                <p>&nbsp;</p>
              </div>
            </div>
          </div>
          <div className="userDecision">
            <div className="temoignage">
              <h2>Section interrogatoire</h2>
              <form onSubmit={handleTemoinSubmit}>
                <input type="text" placeholder="Qui interroger ?" value={temoin} onChange={(e) => setTemoin(e.target.value)} />
                <button type="submit" disabled={isLoadingTemoin}>
                  {isLoadingTemoin ? <span className="loader">Veulliez patienter...</span> : "Interroger"}
                </button>
              </form>

              {temoignage.id != 0 && (
                <button onClick={handleListTemoignages} className="histoTemoin">
                  <FontAwesomeIcon icon={faClockRotateLeft} />
                  <span>Témoignages</span>
                </button>
              )}
            </div>
            <div className="accusation">
              <h2>Section accusation</h2>
              <form onSubmit={handleCoupableSubmit}>
                <input type="text" value={coupable} onChange={(e) => setCoupable(e.target.value)} placeholder="Qui est le coupable ?" />
                <button type="submit" disabled={isLoadingGuilty}>
                  {isLoadingGuilty ? <span className="loader">Veulliez patienter...</span> : "Accuser"}
                </button>
              </form>
            </div>
          </div>
        </div>
      </div>
      <Footer />
    </div>
  );
}

export default Game;
