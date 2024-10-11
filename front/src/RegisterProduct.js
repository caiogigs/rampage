import React, { useState } from 'react';

function RegisterProduct({ handleCancel }) {
    // Estados para armazenar os dados do formulário
    const [productName, setProductName] = useState('');
    const [productDetai, setProductDetai] = useState('');
    const [productPrice, setProductPrice] = useState('');
    const [avaliation, setAvaliation] = useState('');
    const [amount, setAmount] = useState('');
    const [img, setImg] = useState(null);

    // Função para lidar com o envio do formulário
    const handleSubmit = (e) => {
        e.preventDefault();

        // Cria o objeto de produto
        const formData = new FormData();
            formData.append('productName', productName);
            formData.append('productDetai', productDetai);
            formData.append('productPrice', parseFloat(productPrice))
            formData.append('avaliation', parseFloat(avaliation)); 
            formData.append('amount', parseInt(amount, 10));

        // Verifica se há imagem selecionada e adiciona ao formData
        if(img){
            formData.append('img', img);
        }


        // Chama a função de registro com os dados do produto
        handleRegisterProd(formData);
    };

    const handleRegisterProd = async (formData) => {
        try {
            const response = await fetch('http://localhost:8080/resgister_Product', {
                method: 'POST',
                body: formData, // Envia como multipart/form-data
            });

            if (response.ok) {
                console.log('Produto registrado com sucesso');
            } else {
                console.error('Erro ao registrar produto');
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
                    <label htmlFor="productDetai" className="form-label">Destalhes do Produto</label>
                    <input
                        type="text"
                        className="form-control"
                        id="productDetai"
                        value={productDetai}
                        onChange={(e) => setProductDetai(e.target.value)}
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
                    <label htmlFor="productPrice" className="form-label">Preço</label>
                    <input
                        type="number"
                        step="0.01"
                        className="form-control"
                        id="productPrice"
                        value={productPrice}
                        onChange={(e) => setProductPrice(e.target.value)}
                        required
                    />
                </div>
                <div className="mb-3">
                    <label htmlFor="avaliation" className="form-label">Avaliação</label>
                    <select
                        className="form-control"
                        id="avaliation"
                        value={avaliation}
                        onChange={(e) => setAvaliation(e.target.value)}
                        required
                    >
                        <option value="">Selecione uma avaliação</option>
                        <option value="0.5">0.5</option>
                        <option value="1">1</option>
                        <option value="1.5">1.5</option>
                        <option value="2">2</option>
                        <option value="2.5">2.5</option>
                        <option value="3">3</option>
                        <option value="3.5">3.5</option>
                        <option value="4">4</option>
                        <option value="4.5">4.5</option>
                        <option value="5">5</option>
                    </select>
                </div>
                <div className="mb-3">
                    <label htmlFor="img" className="form-label">Imagens do Produto</label>
                    <input
                        type="file"
                        className="form-control"
                        id="img"
                        onChange={(e) => setImg(e.target.files[0])} // Armazena o arquivo de imagem selecionado
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
