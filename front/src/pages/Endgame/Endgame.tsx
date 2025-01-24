import React from "react";
import { useLocation, useNavigate} from "react-router-dom";
import "./Endgame.scss";
import Header from "../../components/Header/Header";
import Footer from "../../components/Footer/Footer";

function Endgame() {
  const navigate = useNavigate();
  const location = useLocation();
  const data = location.state;

  function handleRetour() {
    navigate("/");
  }

  if (!data) {
    return (
      <div className="Endgame">
        <Header />
        <div className="Body-endgame">
          <h1>Erreur : aucune donnée trouvée</h1>
        </div>
        <Footer />
      </div>
    );
  }

  return (
    <div className="Endgame">
      <Header />
      <div className="Body-endgame">
        <div className="end-game__container">
          <h1 className="end-game__title">Verdict !</h1>
          <div className="end-game__trophy">
            <img
              src="https://cdn-icons-png.flaticon.com/512/1827/1827607.png"
              alt="Trophy"
            />
          </div>
          {/* <p className="end-game__time">Temps : </p> */}
          <div className="end-game__description">
            <p>
              { data.description }
            </p>
          </div>
          <button className="end-game__button"  onClick={() => handleRetour()}>Retour</button>
        </div>
      </div>
      <Footer />
    </div>
  );
}

export default Endgame;
