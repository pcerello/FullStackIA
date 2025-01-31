import React from "react";
import "./Modal.scss";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCircleXmark } from "@fortawesome/free-solid-svg-icons";

function Modal(props: { open: boolean; content: { id: number, description: String }[] | { id: number, description: String }; title: string; setIsModal: (value: boolean) => void }) {
  const closeModal = () => {
    console.log("close modal");
    props.setIsModal(false);
  };



  return (
    <div className={"Modal" + (props.open ? " open" : "")}>
      <div className="modal-content">
        <FontAwesomeIcon icon={faCircleXmark} className="iconClose" onClick={closeModal} />
        <h2>{props.title}</h2>
        <div className="textWrapper">
          {
            // si props.content est un tableau, on affiche chaque élément du tableau
            Array.isArray(props.content)
              ?
              (props.content.length > 0 && // si le tableau n'est pas vide
                // temoignages dans l'ordre décroissant
                props.content.sort((a, b) => b.id - a.id).map((item, index) => (

                  <React.Fragment key={item.id}>
                    <p>
                      { // typer props.content pour éviter l'erreur
                        0 === index ? "Dernier témoignage : " : "Témoignage n°" + item.id + " : "
                      }
                      {item.description}
                    </p>
                    {(props.content as { id: number, description: String }[]).length === index + 1 ? "" : <hr />}
                  </React.Fragment>
                ))) || <p>Aucun témoignage</p>



              : // sinon, on affiche juste  
              // texte
              <p>{props.content.description}</p>
          }
        </div>
      </div>
    </div>
  );
}

export default Modal;
