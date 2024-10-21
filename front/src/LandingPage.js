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

function LandingPage() {

    return (
        <div>
            <header>
                <nav className="navbar section-content">
                    <a href="#" className="nav-logo">
                        <h2 className="logo-text">Rampage Store</h2>
                    </a>
                    <ul className="nav-menu">
                        <button id="menu-close-button" className="fas fa-times" />
                        <li className="nav-item">
                            <a href="#" className="nav-link">
                                HOME
                            </a>
                        </li>
                        <li className="nav-item">
                            <a href="#" className="nav-link">
                                SNEAKERS
                            </a>
                        </li>
                        <li className="nav-item">
                            <a href="#" className="nav-link">
                                ROUPAS
                            </a>
                        </li>
                        <li className="nav-item">
                            <a href="#" className="nav-link">
                                CONTATO
                            </a>
                        </li>
                        <li className="nav-item">
                            <a href="#" className="nav-link">
                                SOBRE NÓS
                            </a>
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
                            <h3 className="subtitle">Os melhores preço é aqui</h3>
                            <p className="description">Colocar uma texto aqui</p>
                            <div className="buttons">
                                <a href="#" className="button order-now">
                                    Compre Agora
                                </a>
                                <a href="#" className="button contact-us">
                                    Contato{" "}
                                </a>
                            </div>
                        </div>
                        <div className="hero-image-wrapper">
                            <img
                                src={tenMenu}
                                alt="Hero"
                                className="hero-image"
                            />
                        </div>
                    </div>
                </section>
                {/*About section*/}
                <section className="about-section">
                    <div className="about-details">
                        <h2 clas="section-title">About Us</h2>
                        <p className="text">TEXTO OU PRODUTOS</p>
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
                            <a href="#" className="social-link">
                                <i className="fa-brands fa-facebook"> </i>
                            </a>
                            <a href="#" className="social-link">
                                <i className="fa-brands fa-instagram"> </i>
                            </a>
                            <a href="#" className="social-link">
                                <i className="fa-brands fa-whatsapp"> </i>
                            </a>
                        </div>
                        <p className="policy-text">
                            <a href="#" className="policy-link">
                                Privacy policy
                            </a>
                            <span className="separator">•</span>
                            <a href="#" className="policy-link">
                                Refund policy
                            </a>
                        </p>
                    </div>
                </footer>
            </main>
            {/*Link custom script */}
        </div>
    )

}

export default LandingPage;