import React from "react";
import { useNavigate } from "react-router-dom";

const NotAuth: React.FC = () => {
  const navigate = useNavigate();

  const handleLoginClick = () => {
    navigate("/");
  };
  return (
    <div>
      <h1>Você não está autenticado</h1>
      <p>Por favor, faça login para acessar esta página.</p>
    <button onClick={handleLoginClick}>Fazer Login</button>
    </div>
  );
};

export default NotAuth;
