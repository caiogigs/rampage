import { useEffect, useState } from "react";
import Footer from "../../../../Components/Footer/footer";
import Barra from "../../../../Components/Navbar/Navbar";
import cartService from "../../../../Services/CartService/CartService";
import useCheckoutService from "../../../../Services/CheckoutService";
import "./Payment.css";

const Payment = () => {
  const [paymentMethods, setPaymentMethods] = useState([]);
  const [selectedMethods, setSelectedMethods] = useState({});

  useEffect(() => {
    const fetch = async () => {
      // const data = await new CrudService("/auth").doGet("/payments-methods");

      const data = [
        {
          id: 1,
          method: "BOLETO",
        },
        {
          id: 2,
          method: "PIX",
        },
      ];

      if (data) {
        await setPaymentMethods(data);
        setSelectedMethods(data[0]);
        selectMethods(data[0]);
      }
    };

    fetch();
  }, []);

  const {realizaCheckout, abrirPaginaEndereco} = useCheckoutService();
  const returnTo = () => {
    abrirPaginaEndereco();
  };

  const retomarCheckout = () => {
    realizaCheckout();
  };

  const selectMethods = async (method) => {
    cartService.setPaymentMethod(method);
    setSelectedMethods(method);
  };

  const getMethods = () => {
    return (
      <>
        <div>
          {paymentMethods.map((method, index) => {
            return (
              <div key={index} className="row">
                <div>
                  <input
                    className="col-1 m-auto"
                    type="radio"
                    name="methods"
                    id={`method-${index}`}
                    value={index}
                    onChange={() => selectMethods(method)}
                    checked={method === selectedMethods}
                  />
                  <label
                    className="col-10 mb-4 ps-3 border-left"
                    htmlFor={`method-${index}`}
                  >
                    <h4>{method.method}</h4>
                  </label>
                </div>
              </div>
            );
          })}
        </div>
      </>
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
            {!paymentMethods ? (
              <div className="row">
                <p>Carregando dados ...</p>
              </div>
            ) : (
              getMethods()
            )}
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
                    onClick={() => retomarCheckout()}
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
      <footer>
        <Footer />
      </footer>
    </>
  );
};

export default Payment;
