// Importações
import { FunctionComponent, useCallback, useState } from "react";
import { useNavigate } from 'react-router-dom';

import { loginUser } from '../../services/auth';
import { Button, TextField } from "@mui/material";
import "./Login.css";

// Definindo o componente de Login como uma função de componente funcional
const Login: FunctionComponent = () => {
  // Definindo estados locais para o email e senha do usuário
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  // Utilizando o hook `useNavigate` para acessar a função de navegação do React Router
  const navigate = useNavigate();

  // Função de callback para manipular um clique no texto de placeholder1
  const onPlaceholderText1Click = useCallback(() => {
    // Comentário: Esta função pode ser usada para redirecionar o usuário para a página de cadastro
  }, []);

  // Função de callback para lidar com o clique no botão de login
  const onButtonClick = useCallback(async () => {
    // Obtendo os dados de login a partir do estado local
    const loginData = getLoginData();
    console.log(loginData);

    try {
      // Chamando a função de login e aguardando a resposta
      const data = await loginUser(email, password);
      console.log('Login bem-sucedido:', data);
      // Faça algo com os dados retornados, como armazenar o token de autenticação e redirecionar o usuário
      navigate('/projects'); // Redirecionando o usuário para a página de projetos após o login bem-sucedido

    } catch (error) {
      console.error('Erro durante o login:', error);
    }
  }, [email, password]);

  // Função auxiliar para obter os dados de login a partir dos estados locais
  const getLoginData = () => {
    return {
      login: email,
      password: password,
    };
  };

  // Renderização do componente de login
  return (
    <div className="login">
      {/* Estrutura de layout do componente */}
      <div className="frame">
        {/* Conteúdo do frame1 */}
        <div className="frame1">
          {/* Imagem ou conteúdo específico do frame1 */}
          <img
            className="system-2660914-960-720-1-icon"
            alt=""
            src="/system2660914-960-720-1@2x.png"
          />
        </div>
        {/* Conteúdo do frame2 */}
        <div className="frame2">
          <div className="left-content">
            <div className="left-content-inner">
              <div className="lineup-parent">
                {/* Texto específico do frame2 */}
                <b className="lineup">LineUp</b>
                <div className="saiba-sempre-o">
                  Saiba sempre o melhor caminho
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      {/* Estrutura de layout do componente */}
      <div className="frame3">
        <div className="frame4">
          <div className="frame5" />
        </div>
        <div className="frame6">
          <div className="frame7">
            <div className="frame8">
              <div className="placeholder-parent">
                {/* Textos específicos do frame8 */}
                <div className="placeholder">Não tem uma conta? </div>
                <div className="placeholder1" onClick={onPlaceholderText1Click}>
                  Crie
                </div>
              </div>
            </div>
          </div>
          <div className="frame9">
            <div className="frame10">
              <div className="frame11">
                <div className="right-content">
                  {/* Texto específico do frame11 */}
                  <b className="bem-vindo-de-volta">Bem-vindo de volta!</b>
                  <div className="frame12">
                    {/* Botão de login */}
                    <Button
                      className="button"
                      sx={{ width: 307 }}
                      color="primary"
                      size="large"
                      variant="contained"
                      onClick={onButtonClick} // Associando a função de callback ao clique no botão
                    >
                      Login
                    </Button>
                    <div className="frame13">
                      {/* Campos de entrada de email e senha */}
                      <TextField
                        className="email"
                        color="primary"
                        label="E-mail"
                        sx={{ width: 307 }}
                        variant="outlined"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)} // Atualizando o estado local do email
                      />
                      <TextField
                        className="password"
                        color="primary"
                        label="Senha"
                        sx={{ width: 307 }}
                        variant="outlined"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)} // Atualizando o estado local da senha
                      />
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

// Exportando o componente de Login para ser usado em outras partes da aplicação
export default Login;
