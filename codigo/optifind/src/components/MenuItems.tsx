// Definindo o array "MenuItems" que representa os itens do menu de navegação
export const MenuItems: {
  title: string;
  url: string;
  cName: string;
  icon: string;
}[] = [
  {
    title: "Projetos", // Título do primeiro item do menu
    url: "/projects", // URL associada ao primeiro item do menu
    cName: "nav-links", // Classe de estilo CSS para o primeiro item do menu
    icon: "fa-solid fa-house-user", // Ícone associado ao primeiro item do menu
  },
  {
    title: "Conta", // Título do segundo item do menu
    url: "/profile", // URL associada ao segundo item do menu
    cName: "nav-links", // Classe de estilo CSS para o segundo item do menu
    icon: "fa-solid fa-gear", // Ícone associado ao segundo item do menu
  },
  {
    title: "Logout", // Título do terceiro item do menu
    url: "/", // URL associada ao terceiro item do menu
    cName: "nav-links", // Classe de estilo CSS para o terceiro item do menu
    icon: "fa-solid fa-user", // Ícone associado ao terceiro item do menu
  },
];
