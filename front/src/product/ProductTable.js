import React, { useEffect, useState } from "react";
import RegisterProduct from "./RegisterProduct";

function ProductTable() {
  const [products, setProducts] = useState([]); // Estado para armazenar produtos
  const [loading, setLoading] = useState(true); // Estado para controlar o carregamento
  const [error, setError] = useState(null); // Estado para armazenar erros, se houver
  const [showRegisterProductForm, setShowRegisterProductForm] = useState(false);
  const [searchTerm, setSearchTerm] = useState(''); // Estado para armazenar o termo de pesquisa

  const searchProductByName = async (term = '') => {
    try {
      const response = await fetch(`http://localhost:8080/produtos_contem_palavra?term=${encodeURIComponent(term)}`);
      const data = await response.json();
      setProducts(data); // Atualiza a lista de produtos com os resultados da pesquisa
    } catch (error) {
      console.error('Erro ao buscar produtos:', error);
    }
  };

  // Função para buscar produtos da API
  const fetchProducts = async () => {
    setLoading(true); // Inicia o estado de carregamento
    try {
      const response = await fetch("http://localhost:8080/listar_produto");
      if (!response.ok) {
        throw new Error("Erro ao buscar produtos");
      }
      const data = await response.json();
      setProducts(data); // Armazena os produtos recebidos
    } catch (error) {
      setError("Erro ao buscar produtos.");
      console.error("Erro ao buscar produtos:", error);
    } finally {
      setLoading(false); // Termina o estado de carregamento
    }
  };

  const handleRegisterProd = async (formData) => {
    try {
        const response = await fetch('http://localhost:8080/register_Product', {
            method: 'POST',
            body: formData, // Envia como multipart/form-data
        });

        const data = await response.json();

        if (response.ok) {
          fetchProducts(); // Recarrega a lista de produtos após o registro
          setShowRegisterProductForm(false); // Oculta o formulário após o registro
          return data.message;
        } else {
            const errorMessage = data?.message || 'Erro desconhecido. Tente novamente.'; 
            alert(errorMessage);
            console.error('Erro ao registrar produto:', errorMessage);
        }
    } catch (error) {
      console.error('Erro ao enviar o formulário:', error);
      alert('Erro ao registrar produto. Por favor, tente novamente.');
    }
  };


  const handleAddProductClick = () => {
    setShowRegisterProductForm(true); // Mostra o formulário de registro
  };

  const handleCancel = () => {
    setShowRegisterProductForm(false); // Corrigido aqui
    fetchProducts(); // Recarrega a lista de produtos após o cancelamento
  };

  useEffect(() => {
    fetchProducts(); // Busca produtos quando o componente é montado
  }, []);

  useEffect(() => {
    if (searchTerm) {
      searchProductByName(searchTerm); // Busca produtos quando o termo de pesquisa muda
    } else {
      fetchProducts(); // Busca todos os produtos se o termo estiver vazio
    }
  }, [searchTerm]);

  const handleEdit = (id) => {
    console.log(`Editando produto com ID: ${id}`);
    // Você pode redirecionar para um formulário de edição ou abrir um modal
  };

  const handleDeactivate = (id) => {
    // Lógica para inativar o produto
    console.log(`Inativando produto com ID: ${id}`);
    // Você pode chamar uma API para inativar o produto e atualizar a lista
  };

  const handleReactivate = (id) => {
    // Lógica para reativar o produto
    console.log(`Reativando produto com ID: ${id}`);
    // Você pode chamar uma API para reativar o produto e atualizar a lista
  };

  const handleView = (id) => {
    // Lógica para visualizar detalhes do produto
    console.log(`Visualizando produto com ID: ${id}`);
    // Você pode redirecionar para uma página de detalhes do produto
  };


  if (showRegisterProductForm) {
    return <RegisterProduct handleRegisterProd={handleRegisterProd} handleCancel={handleCancel} />;
}
  

  return (
    <div>
      <h2>Lista de Produtos</h2>
      <button type="button" className="btn btn-primary" onClick={handleAddProductClick}>
        + Novo Produto
      </button>
      <input
        type="text"
        placeholder="Buscar produtos..."
        value={searchTerm}
        onChange={(e) => setSearchTerm(e.target.value)}
      />
      {loading && <p>Carregando produtos...</p>}
      {error && <p className="error">{error}</p>}
      {!loading && !error && (
        <table className="table">
          <thead>
            <tr>
              <th>ID</th>
              <th>Nome</th>
              <th>Preço</th>
              <th>Quantidade</th>
              <th>Status</th>
              <th>Ações</th>
            </tr>
          </thead>
          <tbody>
            {products.map((product) => (
              <tr key={product.id}>
                <td>{product.id}</td>
                <td>{product.productName}</td>
                <td>{product.productPrice}</td>
                <td>{product.amount}</td>
                <td>{product.status ? "Ativo" : "Inativo"}</td>
                <td>
                  <button className="btn btn-warning" onClick={() => handleEdit(product.id)}>Alterar</button>
                  {product.status ? (
                    <button className="btn btn-secondary" onClick={() => handleDeactivate(product.id)}>Inativar</button>
                  ) : (
                    <button className="btn btn-success" onClick={() => handleReactivate(product.id)}>Reativar</button>
                  )}
                  <button className="btn btn-primary" onClick={() => handleView(product.id)}>Visualizar</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
      {showRegisterProductForm && (
         <RegisterProduct handleCancel={handleCancel} handleRegisterProd={handleRegisterProd} />
         
      )}
    </div>
  );
}

export default ProductTable;