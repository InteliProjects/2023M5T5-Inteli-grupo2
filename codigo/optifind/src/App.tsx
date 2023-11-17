// Importando o arquivo de estilos CSS para ser usado no aplicativo
import "./styles.css";

// Importando componentes do React Router para configurar as rotas da aplicação
import { Route, Routes } from "react-router-dom";

// Importando os componentes das páginas que serão renderizadas nas rotas
import Projects from "./pages/Projects/Projects";
import Graph from "./pages/Graph/Graph"
import Login from "./pages/Login/Login";
import NotAuth from "./pages/NotAuth/NotAuth";
import Profile from "./pages/Profile/Profile";

// Função principal que representa o aplicativo
function App() {
  return (
    <div className="App">
      {/* Definindo as rotas da aplicação usando o componente "Routes" */}
      <Routes>
        {/* Rota para a página de login, acessada quando a URL for "/" */}
        <Route path="/" element={<Login />} />
        
        {/* Rota para a página do gráfico, acessada quando a URL for "/graph" */}
        <Route path="/graph/:id" element={<Graph />} />
        <Route path="/nao-autenticado" element={<NotAuth />} />
        <Route path="/projects" element={<Projects />}></Route>
        <Route path="/profile" element={<Profile />}></Route>
      </Routes>
    </div>
  );
}

// Exportando o componente "App" para ser usado em outras partes do aplicativo
export default App;
