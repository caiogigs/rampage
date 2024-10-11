import React, { useEffect, useState } from "react";

import RegisterProduct from "./RegisterProduct";

function ProductTable() {
  const [products, setProducts] = useState([]); // Estado para armazenar produtos
  const [loading, setLoading] = useState(true); // Estado para controlar o carregamento
  const [error, setError] = useState(null); // Estado para armazenar erros, se houver
  const [cadastroProduto, setCadastroProduto] = useState(false); // Estado para controlar o carregamento dos dados

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

  const cadastraproduto = () => {
    setCadastroProduto(true);

  };

  useEffect(() => {
    fetchProducts(); // Busca produtos quando o componente é montado
  }, []);

  return (
    <div>
      <h2>Lista de Produtos</h2>
      <button
        type="button"
        className="btn btn-primary"
        onClick={cadastraproduto}
      >
        {" "}
        Cadastrar
      </button>
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
            </tr>
          </thead>
          <tbody>
            {products.map((product) => (
              <tr key={product.id}>
                <td>{product.id}</td>
                <td>{product.productName}</td>
                <td>{product.price}</td>
                <td>{product.amount}</td>
                <td>{product.status === true ? "Ativo" : "Inativo"}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
      {cadastroProduto ? (
        <>
        <div>
          <>
            <RegisterProduct />
          </>
        </div>
        </>
      ) : (<div></div>)}
  
    </div>
  )
}

export default ProductTable;
