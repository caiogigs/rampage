import React, { useState } from 'react';

function RegisterForm({ handleRegister, handleCancel }) {
    const [formData, setFormData] = useState({
        name: '',
        birthDate: '',
        cpf: '',
        email: '',
        password: '',
        gender: '',
        confirmPassword: '', // Novo campo para confirmação de senha
        role: 'STOKIST'
    });
    const [error, setError] = useState(''); // Estado para armazenar mensagens de erro

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value
        });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        
        // Verifica se as senhas são iguais
        if (formData.password !== formData.confirmPassword) {
            setError('As senhas não coincidem.');
            return;
        }

        // Reseta a mensagem de erro se a validação for bem-sucedida
        setError('');

        // Chamando a função handleRegister e passando o formData
        handleRegister(formData)
            .then(() => {
                alert('Cadastro realizado com sucesso!');
            })
            .catch((err) => {
                console.error('Erro ao cadastrar:', err);
                alert('Erro ao realizar cadastro.');
            });
    };

    return (
        <form onSubmit={handleSubmit}>
            <input 
                type="text" 
                name="name" 
                value={formData.name} 
                onChange={handleInputChange} 
                placeholder="Nome" 
                className="form-control" 
                required 
            />
             <input 
                type="text" 
                name="birthDate" 
                value={formData.birthDate} 
                onChange={handleInputChange} 
                placeholder="Data de Nascimento" 
                className="form-control" 
                required 
            />
            <input 
                type="text" 
                name="cpf" 
                value={formData.cpf} 
                onChange={handleInputChange} 
                placeholder="CPF" 
                className="form-control" 
                required 
            />
            <input 
                type="email" 
                name="email" 
                value={formData.email} 
                onChange={handleInputChange} 
                placeholder="Email" 
                className="form-control" 
                required 
            />
            <input 
                type="password" 
                name="password" 
                value={formData.password} 
                onChange={handleInputChange} 
                placeholder="Senha" 
                className="form-control" 
                required 
            />
            <input 
                type="password" 
                name="confirmPassword" 
                value={formData.confirmPassword} 
                onChange={handleInputChange} 
                placeholder="Confirmar Senha" 
                className="form-control" 
                required 
            />
            <input 
                type="text" 
                name="gender" 
                value={formData.gender} 
                onChange={handleInputChange} 
                placeholder="Genero" 
                className="form-control" 
                required 
            />
            <select 
                name="role" 
                value={formData.role} 
                onChange={handleInputChange} 
                className="form-control" 
                required
            >
                <option value="ADMIN">Administrador</option>
                <option value="STOKIST">Estoquista</option>
            </select>

            {/* Exibe a mensagem de erro se houver */}
            {error && <div className="alert alert-danger">{error}</div>}

            <div>
                <button type="submit" className="btn btn-primary">Registrar</button>
                <button type="button" onClick={handleCancel} className="btn btn-secondary">Cancelar</button>
            </div>
        </form>
    );
}

export default RegisterForm;
