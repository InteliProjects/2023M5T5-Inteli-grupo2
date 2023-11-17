// Importações
import React, { useEffect, useState } from "react";
import fundo from "../assets/project.png";
import "./Card.css";
import ProjectModal from "./ProjectModal";



// Definindo o componente "AddProjectCard" como um componente funcional
const AddProjectCard: React.FC = () => {
  
    const [isModalOpen, setIsModalOpen] = useState(false);
  
    const openModal = () => {
      setIsModalOpen(true);
    };
  
    const closeModal = () => {
      console.log('closeModal called');
      setIsModalOpen(false);
    };
    
  return (
    // Estrutura do cartão de adicionar projeto
    <div className="single__project__card" onClick={openModal}>
      {/* Div para exibir a imagem de fundo do cartão */}
      <div className="project__img">
        <img src={fundo} alt="Adicionar Projeto" />
      </div>

      {/* Div para exibir o conteúdo do cartão de adicionar projeto */}
      <div className="add-project__content">
        {/* Título do cartão de adicionar projeto */}
        <h2 className="add-project__title">Criar Projeto</h2>
      </div>

      <ProjectModal isOpen={isModalOpen} onRequestClose={closeModal} />

    </div>
  );
};

// Exportando o componente "AddProjectCard" para ser usado em outras partes da aplicação
export default AddProjectCard;
