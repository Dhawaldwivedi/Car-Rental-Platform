import React from 'react'
import "../App.css"
import { useNavigate } from 'react-router-dom';

export const HeaderComponent = () => {
  const navigate = useNavigate();

  return (
    <div className="header">
                <div>
                    <button className="user-button" onClick={() => navigate("/userlogin")}>User</button>
                    <button className="admin-button" onClick={() => navigate("/adminlogin")}>Admin</button>
                    
                </div>
    </div>
  )
}

//rafc