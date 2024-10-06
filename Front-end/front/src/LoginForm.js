import React from 'react';

function LoginForm({ handleLogin, handleCancel, formData, handleInputChange }) {
    return (
        <form>
            <input 
                type="email" 
                value={formData.email} 
                onChange={handleInputChange} 
                name="email" 
                placeholder="Email" 
                className="form-control" 
                required 
            />
            <input 
                type="password" 
                value={formData.password} 
                onChange={handleInputChange} 
                name="password" 
                placeholder="Senha" 
                className="form-control" 
                required 
            />
            
            <div>
                <input 
                    type="button" 
                    value="Login" 
                    onClick={handleLogin} 
                    className="btn btn-primary"
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
}

export default LoginForm;