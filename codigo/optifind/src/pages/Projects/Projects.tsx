import Navbar from "../../components/Navbar";
import ProjectCard from "../../components/Card";
import AddProjectCard from "../../components/addCard";

import { useCookies } from 'react-cookie';
import { getProjectsByUserId, useApiProjects, addAuthTokenToApiRequestProjects } from '../../services/projects'; 
import "./Projects.css"


import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";


interface Project {
  id: number;
  userId: number;
  projectName: string;
  rpn: string;
  company: string;
}

function Projects() {
  const [ cookiesUser ]= useCookies([ 'userId' ])
  const [ ProjectItems, setProjectItems] = useState<Project[]>([]);
  const api = useApiProjects();

  const [cookiesAuth ] = useCookies(["authToken"]);
  const navigate = useNavigate();


  useEffect(() => {
    if (!cookiesAuth.authToken) {
      navigate("/nao-autenticado");
    }
  }, [cookiesAuth.authToken, navigate]);

  addAuthTokenToApiRequestProjects(api, cookiesUser);

  useEffect(() => {
    const fetchData = async () => {
      const projects = await getProjectsByUserId(api, cookiesUser.userId);
      if (projects) {
        setProjectItems(projects);
      } else {
        console.error('Failed to fetch projects');
      }
    };
    
    fetchData();
  }, [cookiesUser.userId]);

  return (
    <>
      <div style={{ display: "inline-block" }}>
        <Navbar />
      </div>
      <div
        style={{
          display: "grid",
          gridTemplateColumns: "repeat(4, 1fr)",
          marginTop: "160px",
          marginLeft: "10px",
          marginRight: "10px",
          cursor: "pointer"
        }}
      >
        <AddProjectCard />
        <div className="project-container">
        {ProjectItems.map((item) => (
          <ProjectCard key={item.id} item={item} />
        ))}
        </div>
      </div>
    </>
  );
}

export default Projects;
