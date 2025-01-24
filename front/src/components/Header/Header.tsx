import React from "react";
import "./Header.scss";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faUserSecret } from "@fortawesome/free-solid-svg-icons";
import { Link } from "react-router-dom";

function Header(props: { buttonAbend?: boolean }) {
  return (
    <div className="Header">
      <Link to="/" className="Logo">
        <FontAwesomeIcon icon={faUserSecret} />
        <p>PisteMortelle</p>
      </Link>
      {props.buttonAbend && (
        <Link to="/" className="Button-end">
          <button>Abandonner</button>
        </Link>
      )}
    </div>
  );
}

export default Header;
