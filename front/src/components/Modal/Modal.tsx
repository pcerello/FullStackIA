import React from "react";
import "./Modal.scss";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCircleXmark } from "@fortawesome/free-solid-svg-icons";

function Modal(props: { open: boolean; content: string | String[]; title: string; setIsModal: (value: boolean) => void }) {
  const closeModal = () => {
    console.log("close modal");
    props.setIsModal(false);
  };

  const texte =
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam nec purus nec nunc \
    consectetur fermentum. Nullam nec purus nec nunc consectetur fermentum. Nullam nec purus nec nunc \
    consectetur fermentum. Nullam nec purus nec nunc consectetur fermentum. Nullam nec purus nec nunc \
    consectetur fermentum. Nullam nec purus nec nunc consectetur fermentum. Nullam nec purus nec nunc \
    consectetur fermentum. Nullam nec purus nec nunc consectetur fermentum. Nullam nec purus nec nunc \
    consectetur fermentum. Nullam nec purus nec nunc consectetur fermentum. Nullam nec purus nec nunc \
    consectetur fermentum. Nullam nec purus nec nunc consectetur fermentum. Nullam nec purus nec nunc \
    consectetur fermentum. Nullam nec purus nec nunc consectetur fermentum. Nullam nec purus nec nunc \
    consectetur fermentum. Nullam nec purus nec nunc consectetur fermentum. Nullam nec purus nec nunc \
        consectetur fermentum. Nullam nec purus nec nunc consectetur fermentum. Nullam nec purus nec nunc \
    consectetur fermentum. Nullam nec purus nec nunc consectetur fermentum. Nullam nec purus nec nunc \
    consectetur fermentum. Nullam nec purus nec nunc consectetur fermentum. Nullam nec purus nec nunc \
    consectetur fermentum. Nullam nec purus nec nunc consectetur fermentum. Nullam nec purus nec nunc \
    consectetur fermentum. Nullam nec purus nec nunc consectetur fermentum. Nullam nec purus nec nunc \
    consectetur fermentum. Nullam nec purus nec nunc consectetur fermentum. Nullam nec purus nec nunc \
    consectetur fermentum. Nullam nec purus nec nunc consectetur fermentum. Nullam nec purus nec nunc \
    consectetur fermentum. Nullam nec purus nec nunc consectetur fermentum. Nullam nec purus nec nunc ";

  const temoignages = [
    {
      id: 1,
      description:
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam nec purus nec nunc consectetur fermentum. Nullam nec purus nec nunc consectetur fermentum. Nullam nec purus nec nunc consectetur fermentum. Nullam nec purus nec nunc consectetur fermentum.",
    },
    {
      id: 2,
      description:
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam nec purus nec nunc consectetur fermentum. Nullam nec purus nec nunc consectetur fermentum. Nullam nec purus nec nunc consectetur fermentum. Nullam nec purus nec nunc consectetur fermentum.",
    },
    {
      id: 3,
      description:
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam nec purus nec nunc consectetur fermentum. Nullam nec purus nec nunc consectetur fermentum. Nullam nec purus nec nunc consectetur fermentum. Nullam nec purus nec nunc consectetur fermentum.",
    },
    {
      id: 4,
      description:
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam nec purus nec nunc consectetur fermentum. Nullam nec purus nec nunc consectetur fermentum. Nullam nec purus nec nunc consectetur fermentum. Nullam nec purus nec nunc consectetur fermentum.",
    },
  ];
  return (
    <div className={"Modal" + (props.open ? " open" : "")}>
      <div className="modal-content">
        <FontAwesomeIcon icon={faCircleXmark} className="iconClose" onClick={closeModal} />
        <h2>{props.title}</h2>
        <p>
          {
            // si props.content est un tableau, on affiche chaque élément du tableau
            Array.isArray(props.content)
              ? //props.content.map((item, index) => <p key={index}>{item}</p>)

              // temoignages dans l'ordre décroissant

              temoignages.sort((a, b) => b.id - a.id).map((item) => <><p key={item.id}>
                    
                    {item.id === temoignages.length ? "Dernier témoignage : " : "Témoignage n°" + item.id + " : "}
                    {item.description}
                    
                    </p>
                {item.id === 1 ? "" : <hr />}   </> 
                )


              : // sinon, on affiche juste
                //props.content
                texte
          }
        </p>
      </div>
    </div>
  );
}

export default Modal;
