import { useNavigate } from 'react-router-dom';

function HomeBackOffice() {
    // Função chamada ao clicar no botão "Listar Usuários"
    const handleListUsers = () => {
        navigate('/back-users-list')
    };

    // Função chamada ao clicar no botão "Listar Produtos"
    const handleListProducts = () => {
        navigate('/back-product-list')
    };

    // Navegação entre páginas
    const navigate = useNavigate();

    return (

        <div className="home-back-office">
            <h1>Bem-vindo ao Back Office</h1>
            <div>
                <button 
                    type="button" 
                    className="btn btn-primary"
                    onClick={handleListProducts} // Chama a função para listar produtos
                >
                    Listar Produtos
                </button>
                <button 
                    type="button" 
                    className="btn btn-secondary"
                    onClick={handleListUsers} // Chama a função para listar usuários
                >
                    Listar Usuários
                </button>
            </div>
        </div>
    );
}

export default HomeBackOffice;
