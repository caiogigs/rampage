import { useNavigate, useLocation } from "react-router-dom";
import authService from "../auth/AuthService";

const useCheckoutService = () => {
  const navigate = useNavigate(); // useNavigate substitui useHistory
  const location = useLocation();

  const realizaCheckout = () => {
    console.log(authService.isAuthenticated());
    
    if (authService.isAuthenticated()) {
        console.log(1);
        
      redireciona();
    } else {
      abrirLogin();
    }
  };

  const redireciona = () => {
        console.log(2);
        const hasCheckoutEndereco = location.pathname === "/checkout/select-address";
        const hasCheckoutAddEndereco = location.pathname === "/checkout/register-address";
    const hasCheckoutPagamento = location.pathname === "/checkout/payment";

    if (hasCheckoutEndereco) {
        console.log(4);
        abrirPaginaPagamento();
    } else if (hasCheckoutPagamento) {
        console.log(5);
        finalizaCheckout();
    } else {
        console.log(3);
        abrirPaginaEndereco();
    }
  };

  const abrirPaginaEndereco = () => {
    navigate("/checkout/select-address"); // Usando navigate para redirecionar
  };

  const abrirPaginaPagamento = () => {
    navigate("/checkout/payment"); // Usando navigate para redirecionar
  };

  const finalizaCheckout = () => {
    navigate("/order-summary"); // Usando navigate para redirecionar
  };

  const abrirLogin = () => {
    navigate("/checkout/login"); // Usando navigate para redirecionar
  };

  const cadastarEndereco = () => {
    navigate("/checkout/register-address"); // Usando navigate para redirecionar
  };

  return { realizaCheckout, cadastarEndereco };
};

export default useCheckoutService;
