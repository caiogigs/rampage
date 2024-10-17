import React, { useState } from 'react';

const RegisterConsumerForm = () => {
  const [userData, setUserData] = useState({
    name: '',
    birthDate: '',
    cpf: '',
    email: '',
    password: '',
    gender: '',
    role: 'USER_ROLE', // Valor predefinido
    status: true, // Valor predefinido
  });

  const [addressData, setAddressData] = useState({
    cep: '',
    uf: '',
    city: '',
    neighborhood: '',
    logradouro: '',
    number: '',
    complement: '',
    billingAddress: true, // Valor predefinido
    deliveryAddress: true, // Valor predefinido
    status: true, // Valor predefinido
  });

  const handleUserChange = (e) => {
    const { name, value } = e.target;
    setUserData({
      ...userData,
      [name]: value,
    });
  };

  const handleAddressChange = (e) => {
    const { name, value } = e.target;
    setAddressData({
      ...addressData,
      [name]: value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const requestData = {
      registerDTO: userData,
      userAddress: addressData,
    };
    const token = '${api.security.token.secret}';
    try {
      const response = await fetch('http://localhost:8080/register_consumer', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`  // Substitua pelo seu token de autenticação, se necessário
        },
        body: JSON.stringify(requestData)
      });

      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }

      const data = await response.json();
      console.log('Response:', data);
    } catch (error) {
      console.error('Error:', error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Dados do Cliente</h2>
      <input type="text" name="name" value={userData.name} onChange={handleUserChange} placeholder="Nome" required />
      <input type="date" name="birthDate" value={userData.birthDate} onChange={handleUserChange} required />
      <input type="text" name="cpf" value={userData.cpf} onChange={handleUserChange} placeholder="CPF" required />
      <input type="email" name="email" value={userData.email} onChange={handleUserChange} placeholder="Email" required />
      <input type="password" name="password" value={userData.password} onChange={handleUserChange} placeholder="Senha" required />
      <input type="text" name="gender" value={userData.gender} onChange={handleUserChange} placeholder="Gênero" required />

      <h2>Dados do Endereço</h2>
      <input type="text" name="cep" value={addressData.cep} onChange={handleAddressChange} placeholder="CEP" required />
      <input type="text" name="uf" value={addressData.uf} onChange={handleAddressChange} placeholder="UF" required />
      <input type="text" name="city" value={addressData.city} onChange={handleAddressChange} placeholder="Cidade" required />
      <input type="text" name="neighborhood" value={addressData.neighborhood} onChange={handleAddressChange} placeholder="Bairro" required />
      <input type="text" name="logradouro" value={addressData.logradouro} onChange={handleAddressChange} placeholder="Logradouro" required />
      <input type="text" name="number" value={addressData.number} onChange={handleAddressChange} placeholder="Número" required />
      <input type="text" name="complement" value={addressData.complement} onChange={handleAddressChange} placeholder="Complemento" />

      <button type="submit">Registrar</button>
    </form>
  );
};

export default RegisterConsumerForm;
