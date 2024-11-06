import { useEffect, useState } from "react";
import Footer from "../../../../Components/Footer/footer";
import Barra from "../../../../Components/Navbar/Navbar";
import CrudService from "../../../../Services/CRUDService";

const Payment = () => {
  const [paymentMethods, setPaymentMethods] = useState([]);
  const [selectedMethods, setSelectedMethods] = useState([]);

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
        // await setPaymentMethods(data);
        // setPaymentMethods(paymentMethods[0].id);
        // setSelectedMethods(paymentMethods[0].method);
      }
    };

    fetch();
  }, []);

  const addAdress = () => {};

  const retomarCheckout = () => {};

  const selectMethods = (method) => {};

  const getMethods = () => {
    return (
      <>
        <div>
          {paymentMethods.map((method, index) => {
            return (
              <div key={index}>
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
                    onClick={() => addAdress()}
                    className="btn btn-primary w-50"
                  >
                    Adicionar Endereço
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
