import React from "react";
import "./ModalHome.scss";
import { useNavigate } from "react-router-dom";

interface ModalHomeProps {
  isOpen: boolean;
  onClose: () => void;
  data: { id: number; description: string }[];
}

const ModalHome: React.FC<ModalHomeProps> = ({ isOpen, onClose, data }) => {
  const navigate = useNavigate();

  if (!isOpen) return null;

  const handleClick = (id: number) => {
    navigate(`/game/${id}`);
    onClose();
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
              onClick={() => handleClick(item.id)}
            >
              <span># <strong>{item.id} :</strong></span> <span>{item.description.slice(0, 60)}
              {item.description.length > 60 && "..."}</span>
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
};

export default ModalHome;
