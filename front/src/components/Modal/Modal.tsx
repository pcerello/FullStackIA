import React from "react";
import "./Modal.scss";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCircleXmark } from "@fortawesome/free-solid-svg-icons";

function Modal(props: { open: boolean; content: {id: Number, description: String}[]; title: string; setIsModal: (value: boolean) => void }) {
  const closeModal = () => {
    console.log("close modal");
    props.setIsModal(false);
  };


  console.log("props.content", props.content);

  
  return (
    <div className={"Modal" + (props.open ? " open" : "")}>
      <div className="modal-content">
        <FontAwesomeIcon icon={faCircleXmark} className="iconClose" onClick={closeModal} />
        <h2>{props.title}</h2>
        <p>
          {
            // si props.content est un tableau, on affiche chaque élément du tableau
            Array.isArray(props.content)
              ? 
             
              // temoignages dans l'ordre décroissant

              props.content.sort((a, b) => b.id - a.id).map((item) => <><p key={item.id}>
                    
                    {item.id === props.content.length ? "Dernier témoignage : " : "Témoignage n°" + item.id + " : "}
                    {item.description}
                    
                    </p>
                {item.id === 1 ? "" : <hr />}   </> 
                )


              : // sinon, on affiche juste
                props.content[0].description
                // texte
          }
        </p>
      </div>
    </div>
  );
}

export default Modal;
