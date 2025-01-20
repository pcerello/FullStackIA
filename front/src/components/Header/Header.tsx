import React from "react";
import "./Header.scss";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faUserSecret } from "@fortawesome/free-solid-svg-icons";

function Header() {
  return <div className="Header">
    <FontAwesomeIcon icon={faUserSecret} />
    <p>PisteMortelle</p>
  </div>;
}

export default Header;
