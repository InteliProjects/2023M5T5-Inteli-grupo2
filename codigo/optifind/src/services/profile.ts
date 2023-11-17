import axios from 'axios';
import { useCookies } from 'react-cookie';

export const useApi = () => {
    const [cookies] = useCookies(['authToken']);
  
    const api = axios.create({
      baseURL: 'http://localhost:8080/user',
      withCredentials: true,
      
    });
  
    return api;
  }

  export const addAuthTokenToApiRequest = (api: any, cookies: any) => {
    api.interceptors.request.use((config: any) => {
      config.headers.Authorization = `Bearer ${cookies.authToken}`;
      return config;
    });
  };

  export const getUsersById = async (api: any, id_user: any) => {
    try {
      const response = await api.get(`/${id_user}`);
      return response.data;
    } catch (error) {
      console.error(error);
    }
  };