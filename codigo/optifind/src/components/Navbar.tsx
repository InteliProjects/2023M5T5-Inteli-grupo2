// Importando as bibliotecas e módulos necessários
import { Component } from "react";
import { Link } from "react-router-dom";

// Importando os estilos CSS específicos para o componente "Navbar"
import "./NavbarStyles.css";

// Importando o array "MenuItems" que contém os itens do menu de navegação
import { MenuItems } from "./MenuItems";
import { logout } from '../services/auth' 

// Definindo o estado do componente "Navbar"
interface State {
  clicked: boolean; // Um estado booleano para controlar o clique no ícone do menu
}

// Definindo a classe do componente "Navbar" que estende a classe Component do React
class Navbar extends Component<{}, State> {
  constructor(props: {}) {
    super(props);
    this.state = { clicked: false }; // Inicializando o estado "clicked" como falso
  }

  // Função para alternar o estado "clicked" ao clicar no ícone do menu
  handleClick = () => {
    this.setState({ clicked: !this.state.clicked });
  };

  render() {
    return (
      <nav className="NavbarItems">
        <h1 className="navbar-logo">OptiFind</h1>
        <div className="menu-icons" onClick={this.handleClick}>
          <i
            className={this.state.clicked ? "fas fa-times" : "fas fa-bars"}
          ></i>
        </div>

        {/* Lista de itens do menu que é exibida ou oculta com base no estado "clicked" */}
        <ul className={this.state.clicked ? "nav-menu active" : "nav-menu"}>
          {MenuItems.map((item, index) => (
            <li key={index}>
              <Link
                className={item.cName}
                to={item.url}
                onClick={item.title === "Logout" ? logout : undefined}
              >
                <i className={item.icon}></i>
                {/* Título do item do menu */}
                {item.title}
              </Link>
            </li>
          ))}
        </ul>
      </nav>
    );
  }
}

export default Navbar; // Exportando o componente "Navbar" para ser usado em outras partes da aplicação
