import React from "react";
import "./Game.scss";
import Header from "../../components/Header/Header";
import Footer from "../../components/Footer/Footer";
import accusationImage from "../../assets/accusation.png";
import { useNavigate } from "react-router-dom";
import { useState } from "react";

function Game() {

  const navigate = useNavigate();
  const [coupable, setCoupable] = useState("");

  const handleThemeChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setCoupable(event.target.value);
  };

  const handleSubmit = (event: React.FormEvent) => {
    event.preventDefault();
    // Logic to start the game with the entered theme
    console.log("Thème de l'enquête :", coupable);
    // Redirect to the game page using React Router's navigate
    navigate("/endgame");
  };

  return (
    <div className="Game">
      <Header buttonAbend={true}/>
      <div className="Body-game">

        <div className="Top">
        <div className="Description">
          <h1>Scénario du jeu</h1>

          <div className="ombrage">
            <p className="texte">
              Scénario généré et sauvegardé : Voici un scénario d'enquête sur un meurtre dans le thème de Twilight : **Scénario Principal :** Il fait nuit sur
              la petite ville de Forks, où les lumières des maisons sont éteintes et les ombres s'étendent. Un jeune homme a été trouvé mort à la maison d'un
              vampire. La victime est Renard Packard, un jeune type qui avait des difficultés pour gérer son pouvoir. **Personnages :** 1. **La Victime :** Nom
              : Renard Packard Prénom : René Âge : 19 ans Caractère : Innocent et dévoué Alibi : N'a pas été vu ce soir, mais a des amis qui peuvent attester de
              son emplacement avant les événements. 2. **Suspects :** * *Lia McClean*: une jeune vampire nouvelle qui vient d'arriver à Forks pour vivre dans la
              ville avec sa famille. * *Jacob Black*: un élève de lycée améliorant son pouvoir et essayant de garder les choses en main. **Jacob Black** Alibi :
              Était à la ferme de LaFleur en train d'équilibrer sa puissance de la veille au soir. * *Carlisle Cullen*: un vampire vénérable et respecté qui a
              élevé l'entourage des jeunes de Forks. **Carlisle Cullen** Alibi : N'a pas quitté son lit depuis lundi matin. * *Esme Cullen*: la mère des
              vampires, très concernée par la disparition de Renard et son enthousiasme. *Esme Cullen* Alibi : Estait dans sa chambre, en train d'écrire un
              article sur les vampires et leurs relations avec les humains. * *Edward Cullen*: le frère aîné qui ne dit rien à personne. Et c'est précisément
              cela qui fait craquer Edward. **Edward Cullen** Alibi : Se faisait une sieste à l'étage des égouts. * *Bella Swan*: la jeune humaine de Forks qui
              a été victime d'une attaque et est venue vivre auprès les Cullen pour protéger son sang. **Bella Swan** Alibi : Se tenait dans le parc en train
              d'essayer d'apprendre à contrôler sa capacité. 3. **Témoins :** * *Emmett Cullen*: l'oncle de Renard, un peu timide mais très gentil. * *Rosalee
              Cullen*: la sœur de Carlisle, une vampire avec des compétences en alchimie. **Enquête :** La police de Forks demande à Bella et Jacob d'aider dans
              l'enquête sur le meurtre de Renard. Le lieutenant Billy Black, frère jumeau de Jacob, commence à fouiller la ville pour trouver les preuves du
              crime. Les suspectes commencent à s'échapper un par un. La vérité reste cachée. Avec ces informations en main, vous êtes prêt à démarrer votre
              enquête et suivre les pistes jusqu'à découvrir l'auteur du meurtre de Renard Packard. Croyez-moi, cela est prometteur
              <br />
              &nbsp;
            </p>
          </div>
        </div>
        <div className="Testimonials">
          <form className="examination">
            <input type="text" placeholder="Qui voulez-vous interroger ?" />
            <button type="submit">Interroger</button>
          </form>
          <div className="TestimonialsList">
            <button>Témoignages</button>
          </div>
        </div>
      </div>
      <div className="Bottom">

        <div className="Accusation">
          <img src={accusationImage} alt="accusation" className="accusation-image" />
          <h2>Section accusation </h2>
          <form className="accusation"  onSubmit={handleSubmit}>
            <input type="text" value={coupable}
              onChange={handleThemeChange} placeholder="Qui est le coupable ?" />
            <button type="submit">Accuser</button>
          </form>
        </div>
        </div>
      </div>
      <Footer />
    </div>
  );
}

export default Game;
