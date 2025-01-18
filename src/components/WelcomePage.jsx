import React from 'react'
import { useNavigate } from 'react-router-dom';
import { HeaderComponent } from './HeaderComponent';
import { FooterComponent } from './FooterComponent';
import "../App.css";

export const WelcomePage = () => {
  const navigate = useNavigate();

  return (
    <div id="background1">
      <HeaderComponent className="header" />
      
      {/* Welcome text */}
      <h1 className="welcome-text">ROAD READY APP</h1>

      <FooterComponent className="footer" />
    </div>
  );
};
