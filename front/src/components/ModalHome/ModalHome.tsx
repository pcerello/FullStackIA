import React, { useState } from "react";
import "./ModalHome.scss";
import { useNavigate } from "react-router-dom";
import ApiService from "../../services/ApiService";

interface ModalHomeProps {
  isOpen: boolean;
  onClose: () => void;
  data: { id: number; description: string }[];
}

const ModalHome: React.FC<ModalHomeProps> = ({ isOpen, onClose, data }) => {
  const navigate = useNavigate();

  const [evaluations, setEvaluations] = useState<{ id: number; description: string }[]>([]);

  if (!isOpen) return null;

  const handleClick = (id: number) => {
    navigate(`/game/${id}`);
    onClose();
  };

  // DATA HISTORIQUE EVALUATIONS
  async function fetchTemoignages(item: { id: number; description: string }) {
    try {
      console.log("Fetching Temoignages...", item.id);
      const mesEvaluations = await ApiService.get(`scenario/${item.id}/evaluations`);
      setEvaluations(await ApiService.get(`scenario/${item.id}/evaluations`));
      console.log(mesEvaluations);
      navigate(`/history/${item.id}`, { state: { evaluations: mesEvaluations, description: item.description } })

    } catch (error) {
      console.error("Erreur lors de l'appel API :", error);
    }
  }

  // REVOIR LA LISTE DE TEMOIGNAGE
  const handleHistorySubmit = (item: { id: number; description: string }) => {
    console.log("Liste des témoignages");
    fetchTemoignages(item);
  };

  return (
    <div className="overlay" onClick={onClose}>
      <div
        className="modal"
        onClick={(e) => e.stopPropagation()}
      >
        <button className="close-button" onClick={onClose}>
          &times;
        </button>
        <h2>Historique</h2>
        <ul className="modal-list">
          {data.map((item) => (
            <li
              key={item.id}
              className="clickable-item"
            >
              <div className="text">
                <span># <strong>{item.id} :</strong></span> <span title={item.description}>{item.description.slice(0, 70)}
                  {item.description.length > 70 && "..."}</span>
              </div>
              <br />
              <div className="buttons">
                <button className="button" onClick={() => handleHistorySubmit(item)}>Voir</button>
                <button className="button" onClick={() => handleClick(item.id)}>Rejouer</button>
              </div>
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
};

export default ModalHome;
