import { useEffect, useState } from "react";
import Footer from "../../../Components/Footer/footer";
import Barra from "../../../Components/Navbar/Navbar";
import cartService from "../../../Services/CartService/CartService";
import "./Cart.css";
import CrudService from "../../../Services/CRUDService";
import productService from "../../../Services/ProductService/ProductService";
import siteService from "../../../Services/SiteService/SiteService";

const Cart = () => {
  const [products, setProducts] = useState([{}]);
  const [freight, setFreight] = useState({});
  const [subtotal, setSubTotal] = useState(0);
  const [total, setTotal] = useState(0);
  const [cep, setCep] = useState("");
  const [enderecoCep, setEnderecoCep] = useState();

  const freightOptions = [
    {
      price: 7,
      deliveryTime: "2 dias úteis.",
    },
    {
      price: 15,
      deliveryTime: "4 dias úteis.",
    },
    {
      price: 20,
      deliveryTime: "7 dias úteis.",
    },
  ];

  useEffect(() => {
    const cart = cartService.getCart();
    setProducts(cart.products);
    setFreight(cart.freight);
    setSubTotal(cartService.getSubTotalPrice());
    setTotal(cartService.getTotalPrice());
    console.log(cart);
  }, []);

  useEffect(() => {
    // Recalcula o total sempre que o frete é alterado
    const subtotalValue = cartService.getSubTotalPrice();
    setSubTotal(subtotalValue);
    setFreight(freight);
    setTotal(cartService.getTotalPrice());
  }, [freight]);

  const handleBlur = async () => {
    const data = await siteService.doGet(`/get-cep?cep=${cep}`);
    if (data) {
      setEnderecoCep(data);
    }
  };

  const handleFreightChange = (e) => {
    setFreight(freightOptions[e.target.value]);
    cartService.setFreight(freightOptions[e.target.value]);
  };

  const precoFormatado = (price) => {
    return price.toLocaleString("pt-BR", {
      style: "currency",
      currency: "BRL",
      minimumFractionDigits: 2,
      maximumFractionDigits: 2,
    });
  };

  const showAdress = () => {
    return (
      <div className="row adress">
        <div className="row adress-cep mt-3 pb-3 ps-4">
          <p>Cep: {enderecoCep.cep}</p>
          <p>Logradouro: {enderecoCep.logradouro}</p>
          <p>Bairro: {enderecoCep.bairro}</p>
          <p>Estado: {enderecoCep.estado}</p>
          <p>Cidade: {enderecoCep.localidade}</p>
        </div>
        <div className="row address-options mt-3 ps-4">
          {freightOptions.map((option, index) => (
            <div className="row" key={index}>
              <input
                className="col-1"
                type="radio"
                name="freteOptions"
                id={`frete-${index}`}
                value={index}
                onChange={handleFreightChange}
              />
              <label className="col-10" htmlFor={`frete-${index}`}>
                <p>Prazo: {option.deliveryTime}</p>
                <p>Preço: R$ {precoFormatado(option.price)}</p>
              </label>
            </div>
          ))}
        </div>
      </div>
    );
  };

const mainPage = () => {
    return (
        <main className="w-100 h-100">
        <div className="content row carrinho p-2">
          <div className="infos col-8" style={{ border: "solid blue" }}>
            <div className="row titulo">
              <h2>Produtos</h2>
            </div>
            <div className="row products">
              {products.map((prod) => {
                return (
                  <div className="row product mb-3 mt-3 ms-2">
                    <div className="col-7">
                      <p>Produto: {prod.productName}</p>
                      <p>Quantidade: {prod.quantityOrdered}</p>
                      <p>
                        Valor unitário: R$ {prod?.productPrice ? precoFormatado(prod.productPrice) : "00,00"}
                      </p>
                      <p>Detalhes: {prod.productDetai}</p>
                    </div>
                    <div className="col-5">
                      <div className="imageCart">
                        <img
                          alt={`Imagem ${prod.productName}`}
                          src={`data:image/jpeg;base64, ${prod.image64}`}
                        />
                      </div>
                    </div>
                  </div>
                );
              })}
            </div>
            <div className="row w-100 price mt-5">
              <div className="row w-100">
                <p>Subtotal: R$ {precoFormatado(subtotal)}</p>
                <p>
                  Frete: R${" "}
                  {freight.price ? precoFormatado(freight.price) : "00,00"}
                </p>
                <p>Total: R$ {precoFormatado(total)}</p>
              </div>
            </div>
          </div>

          <div className="infos col-4" style={{ border: "solid orange" }}>
            <div className="row titulo">
              <h2>Frete</h2>
              <div className="row w-100 h-25 p-2">
                <div className="row ms-2">
                  <label id="cep">Cep</label>
                </div>
                <div className="row ms-auto">
                  <input
                    className="form-control"
                    id="cep"
                    type="text"
                    maxLength={8}
                    placeholder="_____-___"
                    onBlur={handleBlur}
                    onChange={(e) => setCep(e.target.value)}
                  ></input>
                </div>
                {enderecoCep ? showAdress() : ""}
              </div>
            </div>
          </div>

          <div className="row button w-100 mt-4 d-flex justify-content-center">
            <button className="btn w-50 btn-primary">Finalizar Compra</button>
          </div>
        </div>
      </main>
    );
}

  return (
    <>
      <header>
        <Barra />
      </header>
      {products ? mainPage() : <p>Carregando...</p>}
      <Footer />
    </>
  );
};

export default Cart;
