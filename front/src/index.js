import React from 'react';
import ReactDOM from 'react-dom/client';
import './Css/index.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';


//Paginas
import LoginBackOffice    from './Pages/backoffice/LoginBackoffice/LoginBackOffice';
import UsersList          from './Pages/backoffice/internalUser/UsersList';
import HomeBackOffice     from './Pages/backoffice/HomeBackOffice';
import ProductTable       from './Pages/backoffice/ProductTable';
import UserConsumerLogin  from './Pages/site/UserConsumerLogin';
import ConsumerRegister   from './Pages/site/ConsumerRegister';
import LandingPage        from './Pages/site/LandingPage';
import ProductDetails from './Pages/site/Product-Details/ProductDetails';
import Cart from './Pages/site/Cart/Cart';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
      <Router>
          <Routes>
            <Route path="/"                   element={<LoginBackOffice   />} />
            <Route path="/back-home"          element={<HomeBackOffice    />} />
            <Route path="/back-users-list"    element={<UsersList         />} />
            <Route path="/back-product-list"  element={<ProductTable      />} />
            <Route path="/user-cadastrar"     element={<ConsumerRegister  />} />
            <Route path="/login_consumer"     element={<UserConsumerLogin />} />
            <Route path="/pagina-principal"   element={<LandingPage       />} />
            <Route path="/product-detail"     element={<ProductDetails    />} />
            <Route path="/cart"       	      element={<Cart              />} />
          </Routes>
      </Router>
  </React.StrictMode>
);
