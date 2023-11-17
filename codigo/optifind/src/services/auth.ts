import axios from 'axios';
import { Cookies } from 'react-cookie';

// Criando uma instância da classe Cookies para manipulação de cookies
const cookies = new Cookies();

// Criando uma instância da API Axios configurada com a URL base e opção de enviar cookies
const api = axios.create({
  baseURL: 'http://localhost:8080/auth',
  withCredentials: true,
});

// Função para realizar a autenticação do usuário
export const loginUser = async (login: any, password: any) => {
  try {
    const response = await api.post('/login', { login, password });
    cookies.set('authToken', response.data.token, { path: '/' });
    cookies.set('userId', response.data.id, { path: '/' });
    return response.data;
  } catch (error) { 
    console.error('Error during login:', error);
    throw error;
  }
};

export const logout = async () => {
  try {
    const response = await api.post('/logout');
    cookies.remove('authToken', { path: '/' });
    cookies.remove('userId', { path: '/' });
    return response.data;
  } catch (error) {
    console.error(error);
  }
};

// export const registerUser = async (email, password) => {
//   try {
//     const response = await api.post('/register', { email, password });
//     cookies.set('authToken', response.data.token, { path: '/' });
//     return response.data;
//   } catch (error) {
//     console.error('Error during registration:', error);
//     throw error;
//   }
// };
