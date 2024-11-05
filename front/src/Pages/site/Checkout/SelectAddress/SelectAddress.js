import { useEffect } from "react";
import { useState } from "react";
import authService from "../../../../auth/AuthService";

const SelectAddress = () => {
  const [address, setAddress] = useState(null);

  useEffect(() => {
    const idClient = authService.getIdUser();
    
  }, []);

  const addAdress = () => {};
  const retomarCheckout = () => {};

  return (
    <>
      <main>
        <div className="h-100 w-100">
          <div className="row"></div>
          <div className="row">
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
      </main>
    </>
  );
};

export default SelectAddress;
