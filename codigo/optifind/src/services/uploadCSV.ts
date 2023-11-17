// src/uploadCSV.ts
import axios, { AxiosResponse } from 'axios';

const api = axios.create({
    baseURL: 'http://localhost:8080', // Ajuste a URL base conforme necessário
});

interface UploadResponse {
    message: string;
    // Adicione outros campos conforme necessário
}

export const uploadFiles = async (
    nodesFile: File | null,
    edgesFile: File | null,
): Promise<[AxiosResponse<UploadResponse>, AxiosResponse<UploadResponse>] | void> => {
    const formData = new FormData();
    if (nodesFile) formData.append('file', nodesFile);  // Certifique-se de que o nome 'file' corresponde ao esperado pelo backend
    if (edgesFile) formData.append('file', edgesFile);  // Se necessário, ajuste o nome do parâmetro aqui também

    try {
        const responseNodes = await api.post<UploadResponse>('/csv', formData, {
            headers: {
                'Content-Type': 'multipart/form-data',
            },
        });
        console.log(responseNodes.data);  // Success message from nodes processing

        const responseEdges = await api.post<UploadResponse>('/csv/edges', formData, {
            headers: {
                'Content-Type': 'multipart/form-data',
            },
        });
        console.log(responseEdges.data);  // Success message from edges processing
        
        return [responseNodes, responseEdges];
    } catch (error) {
        console.error('There was an error uploading the files!', error);
    }
};
