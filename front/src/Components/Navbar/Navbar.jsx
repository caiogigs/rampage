import './style.css';

const Barra = (() => {
    return (
      <>
        <nav className="navbar section-content">
          <button className="nav-logo">
            <h2 className="logo-text">Rampage Store</h2>
          </button>
          <ul className="nav-menu">
            <button id="menu-close-button" className="fas fa-times" />
            <li className="nav-item">
              <button
                onClick={() => {
                  /* ação desejada */
                }}
                className="nav-link"
              >
                HOME
              </button>
            </li>
            <li className="nav-item">
              <button
                onClick={() => {
                  /* ação desejada */
                }}
                className="nav-link"
              >
                SNEAKERS
              </button>
            </li>
            <li className="nav-item">
              <button
                onClick={() => {
                  /* ação desejada */
                }}
                className="nav-link"
              >
                ROUPAS
              </button>
            </li>
            <li className="nav-item">
              <button
                onClick={() => {
                  /* ação desejada */
                }}
                className="nav-link"
              >
                CONTATO
              </button>
            </li>
            <li className="nav-item">
              <button
                onClick={() => {
                  /* ação desejada */
                }}
                className="nav-link"
              >
                SOBRE NÓS
              </button>
            </li>
          </ul>
          <button id="menu-open-button" className="fas fa-bars" />
        </nav>
      </>
    )
  })

  export default Barra;