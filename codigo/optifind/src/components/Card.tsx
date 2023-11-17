// Importando a biblioteca React e o componente "Link" do React Router para criar links
import React from "react";
import { Link } from "react-router-dom";

import img_fabrica from '../assets/barris-stell-na-adega.jpg'; // Ajuste o caminho conforme necessário

// Importando estilos CSS específicos para o componente "Card"
import "./Card.css";

// Definindo as propriedades esperadas para o componente "ProjectCard"
interface ProjectCardProps {
  item: {
    id: number;
    userId: number;
    projectName: string;
    rpn: string;
    company: string;
  };
}

const ProjectCard: React.FC<ProjectCardProps> = ({ item }) => {
  const { id, userId, projectName, rpn, company } = item;

  return (
    <Link to={`/graph/${rpn}`} className="single__nft__card">
      <div className="nft__content">
        <h5 className="nft__title">
          {projectName}
        </h5>

      <img className="img-fabrica" src={img_fabrica} />
        {/* Informações sobre o criador do projeto */}
        <div className="creator__info-wrapper">
          <div className="creator__info">
            <div>
              <h6>Empresa</h6>
              <p>{company}</p>
            </div>
          </div>
        </div>

        <div className="creator__info-wrapper">
          <div className="creator__info">
            <div>
              <h6>Relação Projeto - Nó</h6>
              <p>{rpn}</p>
            </div>
          </div>
        </div>

      </div>
    </Link>
  );
};

// Exportando o componente "ProjectCard" para ser usado em outras partes da aplicação
export default ProjectCard;
