import { Link } from "react-router-dom";
import "./style.css";
import { BiCart } from "react-icons/bi";

const Barra = () => {
  return (
    <>
      <nav className="navbar section-content">
        <button className="nav-logo">
          <h2 className="logo-text">Rampage Store</h2>
        </button>
        <ul className="nav-menu">
          <button id="menu-close-button" className="fas fa-times" />
          <li className="nav-item">
            <Link to={"/pagina-principal"}>
              <button className="nav-link">HOME</button>
            </Link>
          </li>
          <li className="nav-item">
            <Link to={"/pagina-principal"}>
              <button className="nav-link">SNEAKERS</button>
            </Link>
          </li>
          <li className="nav-item">
            <Link to={"/pagina-principal"}>
              <button className="nav-link">ROUPAS</button>
            </Link>
          </li>
          <li className="nav-item">
            <Link to={"/pagina-principal"}>
              <button className="nav-link">CONTATO</button>
            </Link>
          </li>
          <li className="nav-item">
            <Link to={"/pagina-principal"}>
              <button className="nav-link">SOBRE NÓS</button>
            </Link>
          </li>
          <li className="nav-item">
            <Link to={"/cart"}>
              <button className="nav-link"> <BiCart></BiCart>CARRINHO</button>
            </Link>
          </li>
        </ul>
        <button id="menu-open-button" className="fas fa-bars" />
      </nav>
    </>
  );
};

export default Barra;
