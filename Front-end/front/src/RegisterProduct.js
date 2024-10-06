import React, { useState } from 'react';

function RegisterProduct({ handleCancel }) {
    // Estados para armazenar os dados do formulário
    const [productName, setProductName] = useState('');
    const [productBrand, setProductBrand] = useState('');
    const [amount, setAmount] = useState('');
    const [price, setPrice] = useState('');
    
    
    
    const [mainImage, setMainImage] = useState('');

    // Função para lidar com o envio do formulário
    const handleSubmit = (e) => {
        e.preventDefault();

        // Cria o objeto de produto
        const product = {
            productName,
            productBrand,
            amount: parseInt(amount, 10),
            price: parseFloat(price),
           
            mainImage,
            imagens: [], // Imagens serão ignoradas por enquanto
        };

        // Chama a função de registro com os dados do produto
        handleRegisterProd(product);
    };

    const handleRegisterProd = async (product) => {
        try {
            const response = await fetch('http://localhost:8080/cadastrarProduto', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(product),
            });

            if (response.ok) {
                
            } else {
                console.error('Erro ao registrar usuário');
            }
        } catch (error) {
            console.error('Erro ao enviar o formulário:', error);
        }
    };

    return (
        <div className="container">
            <h2>Registrar Produto</h2>
            <form onSubmit={handleSubmit}>
                <div className="mb-3">
                    <label htmlFor="productName" className="form-label">Nome do Produto</label>
                    <input
                        type="text"
                        className="form-control"
                        id="productName"
                        value={productName}
                        onChange={(e) => setProductName(e.target.value)}
                        required
                    />
                </div>
                <div className="mb-3">
                    <label htmlFor="productBrand" className="form-label">Marca do Produto</label>
                    <input
                        type="text"
                        className="form-control"
                        id="productBrand"
                        value={productBrand}
                        onChange={(e) => setProductBrand(e.target.value)}
                        required
                    />
                </div>
                <div className="mb-3">
                    <label htmlFor="amount" className="form-label">Quantidade</label>
                    <input
                        type="number"
                        className="form-control"
                        id="amount"
                        value={amount}
                        onChange={(e) => setAmount(e.target.value)}
                        required
                    />
                </div>
                <div className="mb-3">
                    <label htmlFor="price" className="form-label">Preço</label>
                    <input
                        type="number"
                        step="0.01"
                        className="form-control"
                        id="price"
                        value={price}
                        onChange={(e) => setPrice(e.target.value)}
                        required
                    />
                </div>
                <div className="mb-3">
                    <label htmlFor="mainImage" className="form-label">Imagem Principal</label>
                    <input
                        type="text"
                        className="form-control"
                        id="mainImage"
                        value={mainImage}
                        onChange={(e) => setMainImage(e.target.value)}
                    />
                </div>
                
                <div className="mb-3">
                    <button type="submit" className="btn btn-primary">Registrar</button>
                    <button type="button" className="btn btn-secondary ms-2" onClick={handleCancel}>Cancelar</button>
                </div>
            </form>
        </div>
    );
}

export default RegisterProduct;
