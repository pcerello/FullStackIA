import React from "react";
import "./Header.scss";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faUserSecret } from "@fortawesome/free-solid-svg-icons";
import { Link } from "react-router-dom";
function Header() {
  return (
    <Link to="/">
    <div className="Header">
      
        <FontAwesomeIcon icon={faUserSecret} />
        <p>PisteMortelle</p>
      
    </div>
    </Link>
  );
}

export default Header;
