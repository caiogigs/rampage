import { useEffect, useState } from "react";
import Footer from "../../../../Components/Footer/footer";
import Barra from "../../../../Components/Navbar/Navbar";
import authService from "../../../../auth/AuthService";
import cartService from "../../../../Services/CartService/CartService";
import useCheckoutService from "../../../../Services/CheckoutService";
import addressService from "../../../../Services/Address/AddressService";
import pedidoService from "../../../../Services/PedidoService";

const OrderSummary = () => {
  const [products, setProducts] = useState([]);
  const [address, setAddress] = useState({});
  const [paymentMethod, setPaymentMethod] = useState({});

  const { abrirPaginaPagamento, abrirPaginaEndereco } = useCheckoutService();

  useEffect(() => {
    const fetch = async () => {
      const idUser = authService.getIdUser();
      setProducts(await cartService.getItems());

      const methodPayment = await cartService.getPaymentMethod();
      if (!methodPayment) {
        alert("Método de pagamento não selecionado.");
        abrirPaginaPagamento();
        return;
      }
      setPaymentMethod(methodPayment);

      const addressSelected = await addressService.getAddressSelected();
      if (!addressSelected) {
        alert("Endereço não selecionado.");
        abrirPaginaEndereco();
        return;
      }
      setAddress(addressSelected);
    };

    fetch();
  }, []);

  const precoFormatado = (price) => {
    return price.toLocaleString("pt-BR", {
      style: "currency",
      currency: "BRL",
      minimumFractionDigits: 2,
      maximumFractionDigits: 2,
    });
  };

  const returnTo = () => {
    abrirPaginaPagamento();
  };

  const finalizaPedido = () => {
    const order = getOrder();
    pedidoService.finalizarPedido(order);

    

  };

  const getOrder = () => {
    return new Order(
        authService.getIdUser(),
        address.id,
        paymentMethod.id,
        cartService.getSubTotalPrice(),
        cartService.getTotalPrice(),
        cartService.getFreight().price,
        products
    );
  }

  const getPayment = () => {
    return (
      <div className="row mb-3">
        <h4>Método de pagamento</h4>
        <div className="ps-4">
          <p>{paymentMethod.method} </p>
        </div>
      </div>
    );
  };

  const getAddress = () => {
    return (
      <div className="row mb-3">
        <h4>Endereço de entrega</h4>
        <div className="ps-4">
          <p>Cep: {address.cep}, </p>
          <p>
            {address.logradouro} {address.number},{address.complement}
          </p>
          <p>
            {address.neighborhood}, {address.uf}, {address.city}
          </p>
        </div>
      </div>
    );
  };

  const getProducts = () => {
    return (
      <div className="row mb-3 ">
        <h4>Items do pedido</h4>
        {products.map((prod, index) => {
          return (
            <div key={index} className="row mb-4 ps-4">
              <div className="col-7">
                <p>Produto: {prod.productName}</p>
                <p>Quantidade: {prod.quantityOrdered}</p>
                <p>
                  Valor unitário: R${" "}
                  {prod?.productPrice
                    ? precoFormatado(prod.productPrice)
                    : "0,00"}
                </p>
                <p>
                  Valor Total Item: R${" "}
                  {prod?.productPrice
                    ? precoFormatado(prod.productPrice * prod.quantityOrdered)
                    : "0,00"}
                </p>
              </div>
              <div className="col-3">
                <div className="imageCart">
                  {prod.image64 ? (
                    <img
                      alt={`Imagem ${prod.productName}`}
                      src={`data:image/jpeg;base64, ${prod.image64}`}
                    />
                  ) : (
                    <p>Carregando imagens ...</p>
                  )}
                </div>
              </div>
            </div>
          );
        })}
      </div>
    );
  };

  return (
    <>
      <header>
        <Barra />
      </header>
      <main>
        <div className="main">
          <div className="container">
            <h2 className="row">Resumo Pedido</h2>
            <div className="container mt-4">
              {address ? getAddress() : <p>Carregando Endereço ...</p>}
            </div>
            <div className="container mt-4">
              {paymentMethod ? (
                getPayment()
              ) : (
                <p>Carregando Metodo de pagamento ...</p>
              )}
            </div>
            <div className="container mt-4">
              {products.length > 0 ? (
                getProducts()
              ) : (
                <p>Carregando Produtos ...</p>
              )}
            </div>
            <div className="row mb-5 ps-2">
              <h5>
                SubTotal:{" "}
                <span>R$ {precoFormatado(cartService.getSubTotalPrice())}</span>
              </h5>
              <h5>
                Frete:{" "}
                <span>R$ {precoFormatado(cartService.getFreight().price)}</span>
              </h5>
              <h5>
                Total:{" "}
                <span>R$ {precoFormatado(cartService.getTotalPrice())}</span>
              </h5>
            </div>
            <div className="row mb-5">
              <div className="row d-flex justify-content-center">
                <div className="col-4 d-flex justify-content-center">
                  <button
                    onClick={() => returnTo()}
                    className="btn btn-primary w-50"
                  >
                    Voltar
                  </button>
                </div>
                <div className="col-4  d-flex justify-content-center">
                  <button
                    onClick={() => finalizaPedido()}
                    className="btn btn-primary w-50"
                  >
                    Avançar
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </main>
      <Footer />
    </>
  );
};

class Order {
  constructor(
    consumerId = 0,
    addressId = 0,
    paymentMethods = '',
    subTotal = 0,
    total = 0,
    freight = 0,
    orderItems = []
  ) {
    this.consumerId = consumerId;
    this.addressId = addressId;
    this.paymentMethods = paymentMethods;
    this.subTotal = subTotal;
    this.total = total;
    this.freight = freight;
    this.orderItems = orderItems;
  }
}

export default OrderSummary;
