import React from 'react';
import ReactDOM from 'react-dom/client';
import './Css/index.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';


//Paginas
import Appc               from './Appc';
import UsersList          from './UsersList';
import HomeBackOffice     from './HomeBackOffice';
import ProductTable       from './ProductTable';
import UserConsumerLogin  from './UserConsumerLogin';
import ConsumerRegister   from './CunsumerRegister';
import LandingPage        from './LandingPage';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
      <Router>
          <Routes>
            <Route path="/"                   element={<Appc              />} />
            <Route path="/back-home"          element={<HomeBackOffice    />} />
            <Route path="/back-users-list"    element={<UsersList         />} />
            <Route path="/back-product-list"  element={<ProductTable      />} />
            <Route path="/user-cadastrar"     element={<UserConsumerLogin />} />
            <Route path="/register_consumer"  element={<ConsumerRegister  />} />
            <Route path="/pagina-principal"   element={<LandingPage       />} />
          </Routes>
      </Router>
  </React.StrictMode>
);
