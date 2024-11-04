import { useState } from 'react';
import './';
import LoginForm from './login/LoginForm';
import { useNavigate } from 'react-router-dom';

function App() {
  // Estado para dados do formulário de login
  const [formData, setFormData] = useState({ email: '', password: '' });
  const [loginError, setLoginError] = useState('');
  const [isAuthenticated, setIsAuthenticated] = useState(false);

  // Função para lidar com mudanças nos campos de entrada
  const handleInputChange = (event) => {
    setFormData({ ...formData, [event.target.name]: event.target.value });
  };
  

  // Função para realizar o login
  const handleLogin = () => {
    fetch('http://localhost:8080/auth/login', {
      method: 'POST',
      body: JSON.stringify(formData),
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json',
      },
    })
    .then(response => response.json())
    .then(data => {
      if (data.token) {
        localStorage.setItem('authToken', data.token);
        setIsAuthenticated(true); // Atualiza o estado de autenticação
        setLoginError('');
      } else {
        setLoginError('Falha no login. Verifique suas credenciais.');
      }
    })
    .catch(() => setLoginError('Erro ao conectar com o servidor.'));
  };

  // Função para limpar os dados do formulário
  const handleCancel = () => {
    setFormData({ email: '', password: '' });
    setLoginError('');
  };

  // Navegação entre páginas
  const navigate = useNavigate();

  return (
    <div>
      {isAuthenticated ? (
        navigate('/back-home')
      ) : (
        <LoginForm 
          handleLogin={handleLogin} 
          handleCancel={handleCancel} 
          formData={formData} 
          handleInputChange={handleInputChange} 
        />
      )}
      {loginError && <p className="error">{loginError}</p>}
    </div>
  );
}

export default App;
