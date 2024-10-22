import React, { useEffect, useState } from 'react';
import './Css/font-awesome.min.css';
import './Css/LandingPage.css';


//imagens
import g1 from './assets/img/gallery-1.jpeg';
import g2 from './assets/img/gallery-2.jpg';
import g3 from './assets/img/gallery-3.jpg';
import g4 from './assets/img/gallery-4.jpg';
import g5 from './assets/img/gallery-5.jpeg';
import g6 from './assets/img/gallery-6.jpeg';


import tenMenu from './assets/img/tenis-menu.png';

import t1 from './assets/img/tenis1.png';
import t2 from './assets/img/tenis2.png';
import t3 from './assets/img/tenis3.png';
import t4 from './assets/img/tenis4.png';
import t5 from './assets/img/tenis5.png';
import t6 from './assets/img/tenis6.png';

import u1 from './assets/img/user-1.jpg';
import u2 from './assets/img/user-2.jpg';
import u3 from './assets/img/user-3.jpg';
import u4 from './assets/img/user-4.jpg';
import u5 from './assets/img/user-5.jpg';

const LandingPage = () => {
    const [products, setProducts] = useState([]);
    const [error, setError] = useState(null);

    useEffect(() => {
        fetch('http://localhost:8080/todos_produtos') // Ajuste a URL conforme necessário
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok ' + response.statusText);
                }
                return response.json();
            })
            .then(data => {
                console.log('Fetched products:', data); // Log dos dados recebidos
                setProducts(data);
            })
            .catch(error => {
                console.error('Error fetching products:', error);
                setError(error.toString());
            });
    }, []);

    return (
        <div>
           <header>
    <nav className="navbar section-content">
        <button className="nav-logo">
            <h2 className="logo-text">Rampage Store</h2>
        </button>
        <ul className="nav-menu">
            <button id="menu-close-button" className="fas fa-times" />
            <li className="nav-item">
                <button onClick={() => {/* ação desejada */}} className="nav-link">HOME</button>
            </li>
            <li className="nav-item">
                <button onClick={() => {/* ação desejada */}} className="nav-link">SNEAKERS</button>
            </li>
            <li className="nav-item">
                <button onClick={() => {/* ação desejada */}} className="nav-link">ROUPAS</button>
            </li>
            <li className="nav-item">
                <button onClick={() => {/* ação desejada */}} className="nav-link">CONTATO</button>
            </li>
            <li className="nav-item">
                <button onClick={() => {/* ação desejada */}} className="nav-link">SOBRE NÓS</button>
            </li>
        </ul>
        <button id="menu-open-button" className="fas fa-bars" />
    </nav>
</header>
<main>
    {/* Header / Navbar */}
    <section className="hero-section">
        <div className="section-content">
            <div className="hero-details">
                <h2 className="title">Rampage Store</h2>
                <h3 className="subtitle">Os melhores preços estão aqui</h3>
                <p className="description">Colocar um texto aqui</p>
                <div className="buttons">
                    <button onClick={() => {/* ação desejada */}} className="button order-now">Compre Agora</button>
                    <button onClick={() => {/* ação desejada */}} className="button contact-us">Contato</button>
                </div>
            </div>
            <div className="hero-image-wrapper">
                <img src={tenMenu} alt="Hero" className="hero-image" />
            </div>
        </div>
    </section>
                {/*About section*/}
                <section className="about-section">
                <div>
            <h1>Produtos</h1>
            {error && <p className="error-message">{error}</p>}
            <div className="product-list">
                {products.map((item) => (
                    <div key={item.product.id} className="product-card">
                        <h2>{item.product.productName}</h2>
                        <p>{item.product.productDetai}</p>
                        <p>Preço: {item.product.productPrice}</p>
                        <p>Avaliação: {item.product.avaliation}</p>
                        {item.imageDirection && item.imageDirection.length > 0 && (
                            <img 
                                src={item.imageDirection[1]} 
                                alt={item.product.productName} 
                                className="image" 
                            />
                        )}
                    </div>
                ))}
            </div>
        </div>
                </section>
                {/*Menu section*/}
                <section className="menu-section">
                    <h2 className="section-title">Our Menu</h2>
                    <div className="section-content">
                        <ul className="menu-list">
                            <li className="menu-item">
                                <img
                                    src={t1}
                                    alt="Hot Bevarages"
                                    className="menu-image"
                                />
                                <div className="menu-details">
                                    <h3 className="name">Nome Tenis</h3>
                                    <p className="text">description</p>
                                </div>
                            </li>
                            <li className="menu-item">
                                <img
                                    src={t2}
                                    alt="Cold Bevarages"
                                    className="menu-image"
                                />
                                <div className="menu-details">
                                    <h3 className="name">Nome Tenis</h3>
                                    <p className="text">description</p>
                                </div>
                            </li>
                            <li className="menu-item">
                                <img
                                    src={t3}
                                    alt="Refreshment"
                                    className="menu-image"
                                />
                                <div className="menu-details">
                                    <h3 className="name">Nome Tenis</h3>
                                    <p className="text">description</p>
                                </div>
                            </li>
                            <li className="menu-item">
                                <img
                                    src={t4}
                                    alt="Special Combo"
                                    className="menu-image"
                                />
                                <div className="menu-details">
                                    <h3 className="name">Nome Tenis</h3>
                                    <p className="text">description</p>
                                </div>
                            </li>
                            <li className="menu-item">
                                <img
                                    src={t5}
                                    alt="Desserts"
                                    className="menu-image"
                                />
                                <div className="menu-details">
                                    <h3 className="name">Nome Tenis</h3>
                                    <p className="text">description</p>
                                </div>
                            </li>
                            <li className="menu-item">
                                <img
                                    src={t6}
                                    alt="Burger & Frenchfries"
                                    className="menu-image"
                                />
                                <div className="menu-details">
                                    <h3 className="name">Nome Tenis</h3>
                                    <p className="text">description</p>
                                </div>
                            </li>
                        </ul>
                    </div>
                </section>
                {/*Testimonials section*/}
                <section className="testimonials-section">
                    <h2 className="section-title">Testimonials</h2>
                    <div className="section-content">
                        <div className="section-container swiper">
                            <div className="slider-wrapper">
                                <ul className="testimonials-list swiper-wrapper">
                                    <li className="testimonial">
                                        <img
                                            src={u1}
                                            alt="User"
                                            className="user-image"
                                        />
                                        <h3 className="name">Nome Produto</h3>
                                        <i className="feedback">Sobre</i>
                                    </li>
                                    <li className="testimonial">
                                        <img
                                            src={u2}
                                            alt="User"
                                            className="user-image"
                                        />
                                        <h3 className="name">Nome Produto</h3>
                                        <i className="feedback">Sobre</i>
                                    </li>
                                    <li className="testimonial">
                                        <img
                                            src={u3}
                                            alt="User"
                                            className="user-image"
                                        />
                                        <h3 className="name">Nome Produto</h3>
                                        <i className="feedback">Sobre</i>
                                    </li>
                                    <li className="testimonial">
                                        <img
                                            src={u4}
                                            alt="User"
                                            className="user-image"
                                        />
                                        <h3 className="name">Nome Produto</h3>
                                        <i className="feedback">Sobre</i>
                                    </li>
                                    <li className="testimonial">
                                        <img
                                            src={u5}
                                            alt="User"
                                            className="user-image"
                                        />
                                        <h3 className="name">Nome Produto</h3>
                                        <i className="feedback">Sobre</i>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </section>
                {/* Gallery section */}
                <section className="gallery-section">
                    <h2 className="section-title">Gallery</h2>
                    <div className="section-content">
                        <ul className="gallery-list">
                            <li className="gallery-item">
                                <img
                                    src={g1}
                                    alt="Gallery"
                                    className="gallery-image"
                                />
                            </li>
                            <li className="gallery-item">
                                <img
                                    src={g2}
                                    alt="Gallery"
                                    className="gallery-image"
                                />
                            </li>
                            <li className="gallery-item">
                                <img
                                    src={g3}
                                    alt="Gallery"
                                    className="gallery-image"
                                />
                            </li>
                            <li className="gallery-item">
                                <img
                                    src={g4}
                                    alt="Gallery"
                                    className="gallery-image"
                                />
                            </li>
                            <li className="gallery-item">
                                <img
                                    src={g5}
                                    alt="Gallery"
                                    className="gallery-image"
                                />
                            </li>
                            <li className="gallery-item">
                                <img
                                    src={g6}
                                    alt="Gallery"
                                    className="gallery-image"
                                />
                            </li>
                        </ul>
                    </div>
                </section>
                {/* Contact section */}
                <section className="contact-section">
                    <h2 className="section-title">Contact Us</h2>
                    <div className="section-content">
                        <ul className="contact-info-list">
                            <li className="contact-info">
                                <i className="fa-solid fa-location-crosshairs" />
                                <p>Avenida paulista , São Paulo, SP 05993460</p>
                            </li>
                            <li className="contact-info">
                                <i className="fa-regular fa-envelope" />
                                <p>contato@rampage.com.br</p>
                            </li>
                            <li className="contact-info">
                                <i className="fa-solid fa-phone" />
                                <p>(11)9 5829-7609</p>
                            </li>
                            <li className="contact-info">
                                <i className="fa-regular fa-clock" />
                                <p>Segunda - Sexta : 9:00 - 17:00</p>
                            </li>
                            <li className="contact-info">
                                <i className="fa-regular fa-clock" />
                                <p>Sabádo - Fechado</p>
                            </li>
                        </ul>
                        <form action="#" className="contact-form">
                            <input
                                type="text"
                                placeholder="Seu nome"
                                className="form-input"
                                required=""
                            />
                            <input
                                type="email"
                                placeholder="Seu email"
                                className="form-input"
                                required=""
                            />
                            <textarea
                                placeholder="Menssagem"
                                className="form-input"
                                required=""
                                defaultValue={" "}
                            />
                            <button className="button submit-button">Submit</button>
                        </form>
                    </div>
                </section>
                {/* footer section*/}
                <footer className="footer-section">
    <div className="section-content">
        <p className="copyright-text">2024 - Rampage Store</p>
        <div className="social-link-list">
            <button onClick={() => {/* ação desejada */}} className="social-link">
                <i className="fa-brands fa-facebook"> </i>
            </button>
            <button onClick={() => {/* ação desejada */}} className="social-link">
                <i className="fa-brands fa-instagram"> </i>
            </button>
            <button onClick={() => {/* ação desejada */}} className="social-link">
                <i className="fa-brands fa-whatsapp"> </i>
            </button>
        </div>
        <p className="policy-text">
            <button onClick={() => {/* ação desejada */}} className="policy-link">
                Privacy policy
            </button>
            <span className="separator">•</span>
            <button onClick={() => {/* ação desejada */}} className="policy-link">
                Refund policy
            </button>
        </p>
    </div>
</footer>
            </main>
            {/*Link custom script */}
        </div>
    )

}

export default LandingPage;