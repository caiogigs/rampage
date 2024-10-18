import React, { useState } from 'react';

const UserConsumerLogin = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [loginError, setLoginError] = useState('');
  const [loading, setLoading] = useState(false);

  const handleLogin = async () => {
    setLoading(true); 
    setLoginError(''); 

    const formData = { email, password }; // Cria o objeto de dados a ser enviado

    try {
      const response = await fetch('http://localhost:8080/auth/login_consumer', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Accept': 'application/json',
        },
        body: JSON.stringify(formData), // Envia os dados no formato correto
      });

      const data = await response.json();

      if (response.ok) {
        if (data.token) {
          localStorage.setItem('authToken', data.token); // Armazena o token no localStorage
          alert('Login bem-sucedido!');
          setLoginError('');
        } else {
          setLoginError('Falha no login. Verifique suas credenciais.');
        }
      } else {
        setLoginError(data.message || 'Falha no login.');
      }
    } catch (error) {
      setLoginError('Erro ao conectar com o servidor.');
      console.error('Login error:', error);
    } finally {
      setLoading(false); 
    }
  };

  const handleCancel = () => {
    setEmail('');
    setPassword('');
  };

  return (
    <form onSubmit={(e) => { e.preventDefault(); handleLogin(); }}>
      <div>
        <input 
          type="email" 
          value={email} 
          onChange={(e) => setEmail(e.target.value)} 
          name="email" 
          placeholder="Email" 
          className="form-control" 
          required
        />
      </div>
      <div>
        <input 
          type="password" 
          value={password} 
          onChange={(e) => setPassword(e.target.value)} 
          name="password" 
          placeholder="Senha" 
          className="form-control" 
          required
        />
      </div>
      {loginError && <div className="error">{loginError}</div>}
      <div>
        <input 
          type="submit" 
          value={loading ? "Carregando..." : "Login"} 
          className="btn btn-primary"
          disabled={loading} // Desabilita o botão enquanto está carregando
        />
        <input 
          type="button" 
          value="Cancelar" 
          onClick={handleCancel} 
          className="btn btn-secondary"
        />
      </div>
    </form>
  );
};

export default UserConsumerLogin;
