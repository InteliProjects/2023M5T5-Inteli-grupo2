import { useCookies } from "react-cookie";
import axios from "axios";

export const useApiNodes = (authToken: any) => {

  // Cria uma instância da API Axios com uma baseURL específica.
  const api = axios.create({
    baseURL: "http://localhost:8080/",
    headers: {
        Authorization: `Bearer ${authToken}`
    }
  });

  return api;
};


// Função assíncrona para obter todos os nós do servidor.
export const getNodes = async (authToken: any) => {
    try {
        const api = useApiNodes(authToken); // Get the api instance
        // Faz uma requisição GET para obter a lista de nós.
        const response = await api.get("/nodes");
        return response.data; // Retorna os dados da resposta.
    } catch (error) {
        console.log("Error getting nodes: ", error); // Em caso de erro, exibe uma mensagem de erro no console.
        throw error; // Lança o erro para que seja tratado pelo código que chama essa função.
    }
};

// Função assíncrona para obter todas as arestas do servidor.
export const getEdges = async (authToken: any) => {
    try {
        const api = useApiNodes(authToken); // Get the api instance
        // Faz uma requisição GET para obter a lista de arestas.
        const response = await api.get("/edges");
        return response.data; // Retorna os dados da resposta.
    } catch (error) {
        console.log("Error getting edges: ", error); // Em caso de erro, exibe uma mensagem de erro no console.
        throw error; // Lança o erro para que seja tratado pelo código que chama essa função.
    }
}