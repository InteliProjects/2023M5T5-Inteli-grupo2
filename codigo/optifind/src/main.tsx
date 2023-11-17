// Importando o ReactDOM para renderizar a aplicação no DOM do navegador
import ReactDOM from 'react-dom';

// Importando estilos CSS globais para a aplicação
import './index.css';

// Importando o componente principal da aplicação, "App"
import App from './App';

// Importando o BrowserRouter do React Router para fornecer a funcionalidade de roteamento
import { BrowserRouter } from "react-router-dom";

// Importando componentes do Material-UI para estilização
import {
  CssBaseline,
  StyledEngineProvider,
} from "@mui/material";

// Renderizando a aplicação no elemento com o ID 'root' no DOM
ReactDOM.render(
  // Utilizando o BrowserRouter para encapsular a aplicação e habilitar o roteamento
  <BrowserRouter>
    {/* 
      Utilizando o StyledEngineProvider para permitir o uso do Material-UI 
      e garantir que os estilos sejam injetados corretamente no DOM
    */}
    <StyledEngineProvider injectFirst>
      {/* 
        Aplicando um estilo de reinicialização (reset) com CssBaseline 
        para garantir uma aparência consistente em diferentes navegadores
      */}
      <CssBaseline />
      
      {/* Renderizando o componente principal da aplicação, "App" */}
      <App />
    </StyledEngineProvider>
  </BrowserRouter>,
  // Especificando o elemento no DOM onde a aplicação será renderizada
  document.getElementById('root')
);
