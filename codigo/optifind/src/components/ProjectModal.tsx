// Importando as bibliotecas necessárias
import React, { useEffect, useState } from "react";
import Modal from "react-modal";
import "./ProjectModal.css";
import { useCookies } from "react-cookie";
import { addAuthTokenToApiRequestProjects, createProject, useApiProjects } from "../services/projects";

interface ProjectModalProps {
  isOpen: boolean;
  onRequestClose: () => void;
}

const ProjectModal = ({ isOpen, onRequestClose }: ProjectModalProps) => {
  // Definindo o estado para os campos de entrada
  const [projectName, setName] = useState("");
  const [company, setCompany] = useState("");
  const [rpn, setRpn] = useState("");
  const [cookies] = useCookies(['userId']);
  const api = useApiProjects();
  const [ cookiesAuth ] = useCookies(["authToken"]);
  const [ cookiesUserId ]= useCookies([ 'userId' ])


  useEffect(() => {
    // Adicione o interceptor quando o componente for montado
    addAuthTokenToApiRequestProjects(api, cookiesAuth);
  }, [api, cookiesAuth]);  // Dependências do efeito


  const generateRpn = (company: string) => {
    // Convertendo o nome da empresa para maiúsculas
    const upperCaseCompany = company.toUpperCase();

    // Gerando dois números aleatórios para o início e fim do RPN
    const randomStart = Math.floor(Math.random() * 90) + 10; // Gera um número aleatório entre 10 e 99
    const randomEnd = Math.floor(Math.random() * 90) + 10; // Gera um número aleatório entre 10 e 99

    // Combinando os números aleatórios e o nome da empresa para criar o RPN
    return `${randomStart}${upperCaseCompany}${randomEnd}`;
  };

  // Função para lidar com o envio do formulário
  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    const rpnGenerated = generateRpn(company);
    const userId = cookies.userId;


    // Lógica para criar um novo projeto com os dados fornecidos
    const projectData = {
      projectName,
      rpn: rpnGenerated,
      company,
      userId
    };
    // Convertendo o objeto para uma string JSON e imprimindo no console
    console.log(JSON.stringify(projectData, null, 2));


    try{
      const createdProject = await createProject(api, projectData);
      console.log('Projeto criado com sucesso:', createdProject);
    } catch (error) {
      console.error('Erro ao criar projeto:', error);
    }
  };

  return (
    <Modal
      isOpen={isOpen}
      onRequestClose={onRequestClose}
      contentLabel="Criar Projeto"
      overlayClassName="ReactModal__Overlay"
      className="modal-content" // Adicionando a classe CSS
    >
      <h2 className="modal-title">Criar Projeto</h2>

      <form onSubmit={handleSubmit}>
        <label>
          Nome:
          <input
            type="text"
            value={projectName}
            onChange={(e) => setName(e.target.value)}
          />
        </label>
        <label>
          Empresa:
          <input
            type="text"
            value={company}
            onChange={(e) => setCompany(e.target.value)}
          />
        </label>
        <div className="button-group">
          <button
            type="button"
            onClick={(e) => {
              e.stopPropagation();
              onRequestClose();
            }}
          >
            Fechar
          </button>
          <button type="submit">Criar</button>
        </div>
      </form>
    </Modal>
  );
};

export default ProjectModal;
