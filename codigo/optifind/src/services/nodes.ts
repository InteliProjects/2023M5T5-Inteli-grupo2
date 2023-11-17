// Importa a biblioteca Axios, que é utilizada para fazer requisições HTTP.
import axios from "axios";
// import { useCookies } from "react-cookie";
// import { useCookies } from "react-cookie";

export const useApiNodesTools = () => {
    // const [cookiesAuth] = useCookies(['authToken']);
  
    const api = axios.create({
      baseURL: 'http://localhost:8080/',
      withCredentials: true,
    });
  
    return api;
  }
  
// export const addAuthTokenToApiRequestNodes = (api: any, cookies: any) => {
//     api.interceptors.request.use((config: any) => {
//         config.headers.Authorization = `Bearer ${cookies.authToken}`;
//         return config;
//     });
// };


// Função assíncrona para obter todos os nós do servidor.
export const getNodes = async () => {
    try {
        const api = useApiNodesTools(); // Get the api instance
        // Faz uma requisição GET para obter a lista de nós.
        const response = await api.get("/nodes");
        return response.data; // Retorna os dados da resposta.
    } catch (error) {
        console.log("Error getting nodes: ", error); // Em caso de erro, exibe uma mensagem de erro no console.
        throw error; // Lança o erro para que seja tratado pelo código que chama essa função.
    }
};

// Função assíncrona para obter todas as arestas do servidor.
export const getEdges = async () => {
    try {
        const api = useApiNodesTools(); // Get the api instance
        // Faz uma requisição GET para obter a lista de arestas.
        const response = await api.get("/edges");
        return response.data; // Retorna os dados da resposta.
    } catch (error) {
        console.log("Error getting edges: ", error); // Em caso de erro, exibe uma mensagem de erro no console.
        throw error; // Lança o erro para que seja tratado pelo código que chama essa função.
    }
}

// Função assíncrona para criar um nó no servidor.
export const createNode = async (
    name: string,
    type: string,
    rpn: string,
    volume: any,
    condicao: string
) => {
    try {
        const api = useApiNodesTools(); // Get the api instance
        // Faz uma requisição POST para o servidor com os parâmetros fornecidos.
        const data = {
            name: name,
            type: type,
            rpn: rpn,
            properties: {
                volume: volume,
                condicao: condicao,
            },
        };
        const response = await api.post("/nodes", data);

        return {
            response: response,
            data: data,
        };
        // Retorna a resposta do servidor.
    } catch (error) {
        console.error("Error creating node: ", error); // Em caso de erro, exibe uma mensagem de erro no console.
        throw error; // Lança o erro para que seja tratado pelo código que chama essa função.
    }
};

export const updateNode = async (
    id: string,
    name: string,
    type: string,
    rpn: string,
    volume: any,
    condicao: string
) => {
    try {
        const api = useApiNodesTools(); // Get the api instance
        const data = {
            name: name,
            type: type,
            rpn: rpn,
            properties: {
                volume: volume,
                condicao: condicao,
            },
        };

        const response = await api.put(`/nodes/${parseInt(id)}`, data);

        return {
            response: response,
            data: data,
        };
    } catch (error) {
        console.error("Error updating node: ", error);
        throw error;
    }
};

export const deleteNode = async (
    id: string,
    name: string,
    rpn: string
) => {
    try {
        const api = useApiNodesTools(); // Get the api instance
        const data = {
            id: parseInt(id),
            name: name,
            rpn: rpn,
        };

        await api.delete("/nodes", data);

        console.log("Deu certo em deletar o nó");
        // console.log(response)
    } catch (error) {
        console.error("Error deleting node: ", error);
        throw error;
    }
};


// Função assíncrona para obter um algoritmo do servidor com nós de início e fim opcionais.
export const getAlgorithm = async (
  startNode = "",
  endNode = ""
) => {
  try {
    // Faz uma requisição GET para obter um algoritmo, com nós de início e fim como parâmetros opcionais.

    const api= useApiNodesTools();
    const response = await api.get(`/algorithm/calculate?startNode=${startNode}&endNode=${endNode}`);
    return response.data; // Retorna os dados da resposta.
  } catch (error) {
    console.log("Error getting algorithm: ", error); // Em caso de erro, exibe uma mensagem de erro no console.
    throw error; // Lança o erro para que seja tratado pelo código que chama essa função.
  }
};

export const getAllPathsAlgorithm = async (startNode = "", endNode = "") => {
    try {
        const api = useApiNodesTools();
        const response = await api.get(`allpaths?startNodeName=${startNode}&endNodeName=${endNode}&maxDepth=${9}`)
        return response.data;
    } catch (error) {
        console.log("Error getting all paths algorithm: ", error);
        throw error; 
    }
}