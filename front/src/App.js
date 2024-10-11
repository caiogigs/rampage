import './App.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';

//Paginas
import Appc from './Appc';
import UsersList from './UsersList';
import HomeBackOffice from './HomeBackOffice';
import ProductTable from './ProductTable';

function App() {
  return (
    <div className="App">
        <Router>
            <Routes>
                <Route path="/" element={<Appc />} />
                <Route path="/back-home" element={<HomeBackOffice />} />
                <Route path="/back-users-list" element={<UsersList />} />
                <Route path="/back-product-list" element={<ProductTable />} />
            </Routes>
        </Router>
    </div>
  );
}

export default App;