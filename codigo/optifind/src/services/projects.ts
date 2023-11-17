import axios from 'axios';
import { useCookies } from 'react-cookie';

interface Project {
    id: number;
    userId: number;
    projectName: string;
    rpn: string;
    company: string;
  }  

  interface ProjectData {
    projectName: string;
    rpn: string;
    company: string;
    userId: string;
  }

  export const useApiProjects = () => {
    const [cookies] = useCookies(['authToken']);
  
    const api = axios.create({
      baseURL: 'http://localhost:8080/project',
      withCredentials: true,
      
    });
  
    return api;
  }

  export const addAuthTokenToApiRequestProjects = (api: any, cookies: any) => {
    api.interceptors.request.use((config: any) => {
      config.headers.Authorization = `Bearer ${cookies.authToken}`;
      console.log(config);  // adicione esta linha para verificar a configuração

      return config;
    });
  };

  export const getProjectsByUserId = async (api: any, id_user: any) => {
    try {
      const response = await api.get(`/user/${id_user}`);
      return response.data;
    } catch (error) {
      console.error(error);
    }
  };

  export const createProject = async (api: any, projectData: ProjectData) => {
    try {
      const response = await api.post('/create', projectData);
      return response.data;
    } catch (error) {
      console.error('Erro ao criar projeto:', error);
      throw error;
    }
  };