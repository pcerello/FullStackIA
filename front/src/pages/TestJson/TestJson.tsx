import React from "react";
import "./TestJson.scss";
import enqueteImage from "../../assets/enquete.png";
import Header from "../../components/Header/Header.tsx";
import Footer from "../../components/Footer/Footer.tsx";

function TestJson() {
    // Methode 1 
    const fetchData = async () => {
        try {
            const response = await fetch("http://localhost:8083/genererScenario?question=je veux une enquete a new york", {
                method: "POST"
                ,
                headers: {
                    "Content-Type": "application/json"
                }
            });
            const data = await response.json();
            // const data = await response.text;
            console.log(data);
        } catch (error) {
            const data = 'Erreur lors de la requête:';
            console.error(data, error);
        }
    
    };
        fetchData();

  return (
    <div className="TestJson">
      <Header />
      <div className="Body-testJson">
        <img src={enqueteImage} alt="logo" />
      </div>
      <Footer />
    </div>
  );
}

export default TestJson;
