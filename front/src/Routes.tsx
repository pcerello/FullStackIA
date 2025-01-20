import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Home from './pages/Home/Home.tsx'; // Import de la page Home
import Game from './pages/Game/Game.tsx'; // Import de la page Game
import Endgame from './pages/Endgame/Endgame.tsx'; // Import de la page Endgame
const AppRoutes = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/game" element={<Game />} />
        <Route path="/endgame" element={<Endgame />} />
      </Routes>
    </Router>
  );
};

export default AppRoutes;
