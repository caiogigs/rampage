import React from 'react';
import ReactDOM from 'react-dom/client';
import { Route, BrowserRouter as Router, Routes } from 'react-router-dom';
import './Css/index.css';


//Paginas
import HomeBackOffice from './Pages/backoffice/HomeBackOffice';
import LoginBackOffice from './Pages/backoffice/LoginBackoffice/LoginBackOffice';
import ProductTable from './Pages/backoffice/ProductTable';
import UsersList from './Pages/backoffice/internalUser/UsersList';
import Cart from './Pages/site/Cart/Cart';
import OrderSummary from './Pages/site/Checkout/OrderSummary/OrderSummary';
import Payment from './Pages/site/Checkout/Payment/Payment';
import SelectAddress from './Pages/site/Checkout/SelectAddress/SelectAddress';
import ConsumerRegister from './Pages/site/ConsumerRegister';
import LandingPage from './Pages/site/LandingPage';
import ProductDetails from './Pages/site/Product-Details/ProductDetails';
import UserConsumerLogin from './Pages/site/UserConsumerLogin';
import RegisterAddress from './Pages/site/Checkout/RegisterAddress/RegisterAddress';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
      <Router>
          <Routes>
            <Route path="/"                           element={<LoginBackOffice     />} />
            <Route path="/back-home"                  element={<HomeBackOffice      />} />
            <Route path="/back-users-list"            element={<UsersList           />} />
            <Route path="/back-product-list"          element={<ProductTable        />} />
            <Route path="/user-cadastrar"             element={<ConsumerRegister    />} />
            <Route path="/login_consumer"             element={<UserConsumerLogin   />} />
            <Route path="/pagina-principal"           element={<LandingPage         />} />
            <Route path="/product-detail"             element={<ProductDetails      />} />
            <Route path="/cart"       	              element={<Cart                />} />
            <Route path="/checkout/select-address"    element={<SelectAddress       />} />
            <Route path="/checkout/register-address"  element={<RegisterAddress     />} />
            <Route path="/checkout/payment"           element={<Payment             />} />
            <Route path="/order-summary"              element={<OrderSummary        />} />
            <Route path="/checkout/login"             element={<UserConsumerLogin   />} />
          </Routes>
      </Router>
  </React.StrictMode>
);
