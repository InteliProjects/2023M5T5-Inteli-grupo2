import { useEffect, useState } from "react";
import Navbar from "../../components/Navbar";
import "./Profile.css";
import {
  getUsersById,
  useApi,
  addAuthTokenToApiRequest,
} from "../../services/profile";
import {
  getProjectsByUserId,
  useApiProjects,
  addAuthTokenToApiRequestProjects,
} from "../../services/projects";

import { useNavigate } from "react-router-dom";
import { useCookies } from "react-cookie";
import ProjectCard from "../../components/Card";

interface Project {
  id: number;
  userId: number;
  projectName: string;
  rpn: string;
  company: string;
}

interface User {
  id: number;
  name: string;
  surname: string;
  login: string;
  // ... outros campos que o usuário pode ter ...
}

function Profile() {
  const [cookiesUser] = useCookies(["userId"]);
  const [ProjectItems, setProjectItems] = useState<Project[]>([]);
  const [user, setUser] = useState<User | null>(null);

  const api = useApi();
  const apiProjects = useApiProjects();

  const [cookiesAuth] = useCookies(["authToken"]);
  const navigate = useNavigate();

  useEffect(() => {
    if (!cookiesAuth.authToken) {
      navigate("/nao-autenticado");
    }
  }, [cookiesAuth.authToken, navigate]);

  addAuthTokenToApiRequest(api, cookiesUser);
  addAuthTokenToApiRequestProjects(apiProjects, cookiesUser);

  useEffect(() => {
    const fetchData = async () => {
      const projects = await getProjectsByUserId(
        apiProjects,
        cookiesUser.userId
      );
      console.log("Projects:", projects);
      if (projects) {
        setProjectItems(projects);
      } else {
        console.error("Failed to fetch projects");
      }
    };

    fetchData();
  }, [cookiesUser.userId]);

  useEffect(() => {
    const fetchUser = async () => {
      const fetchedUser = await getUsersById(api, cookiesUser.userId);
      if (fetchedUser) {
        setUser(fetchedUser);
      } else {
        console.error("Failed to fetch user");
      }
    };

    fetchUser();
  }, [cookiesUser.userId]);

  return (
    <div className="main-container">
      <div className="navbar-container">
        <Navbar />
      </div>

      <div>
        <h1 className="title-content">Minhas Informações:</h1>
        <div className="user-info-container">
          <div className="user-info">
            <p className="text-content">
              Nome: {user ? user.name : "Carregando..."}
            </p>
            <p className="text-content">
              Sobrenome: {user ? user.surname : "Carregando..."}
            </p>
            <p className="text-content">
              E-mail: {user ? user.login : "Carregando..."}
            </p>
          </div>
        </div>
        <h1 className="project-title"> Meus Projetos:</h1>
        <div className="project-container">
          {Array.isArray(ProjectItems) &&
            ProjectItems.map((item) => (
              <ProjectCard key={item.id} item={item} />
            ))}
        </div>
      </div>
    </div>
  );
}

export default Profile;
