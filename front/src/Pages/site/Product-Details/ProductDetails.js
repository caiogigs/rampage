import { useEffect, useRef, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import Carrosel from "../../../Components/Carousel/Carousel";
import StarRating from "../../../Components/StarsRating/StarRating";
import cartService from "../../../Services/CartService/CartService";
import siteService from "../../../Services/SiteService/SiteService";
import "./ProductDetails.css";
import Barra from "../../../Components/Navbar/Navbar";

const ProductDetails = () => {
  const location = useLocation();
  const queryParams = new URLSearchParams(location.search);
  const productId = queryParams.get("product");

  const [quantityOrdered, setQuantityOrdered] = useState(0);
  const [product, setProduct] = useState({});
  const imageRef = useRef([]);

  const handleChange = (event) => {
    const value = Number(event.target.value);
    if (value <= product.amount) {
      setQuantityOrdered(value);
    } else {
      setQuantityOrdered(product.amount);
    }
  };

  useEffect(() => {
    const fetchProduct = async () => {
      try {
        const data = await siteService.getProductByID(productId);

        if (data?.product) setProduct(data.product);

        if (data?.imageBase64) imageRef.current = data.imageBase64;
      } catch (error) {
        console.error("Error fetching product data:", error);
      }
    };

    if (productId) {
      fetchProduct();
    }
  }, [productId]);

  const navigate = new useNavigate();

  const saveOnCart = (productOrder) => {
    productOrder.quantityOrdered = quantityOrdered;
    productOrder.image64 = imageRef.current[0];
    cartService.addItem(productOrder);
    navigate("/cart");
  };


  const tela = () => {
    return (
      <>
        <header>
          <Barra />
        </header>
        <main className="w-100 h-100">
          <div className="w-100 product">
            <div className="row">
              <div className="col-6">
                <Carrosel imageBase64={imageRef.current} />
              </div>

              <div className="col-6">
                <div className="rating">
                  <StarRating avaliacao={product.avaliation} />
                </div>
                <div>
                  <p>Produto: {product.productName}</p>
                  <p>Detalhes: </p>
                  <textarea value={product.productDetai}></textarea>
                  <p>Preço: R$ {product.productPrice.toFixed(2)}</p>
                  <p>Quatidade Disponível: {product.amount}</p>
                  <input
                    className="w-25"
                    type="number"
                    id="numberInput"
                    value={quantityOrdered}
                    max={product.amount}
                    min={1}
                    onChange={handleChange} // Chama a função ao mudar o valor
                  />
                </div>
              </div>
            </div>
            <div className="row d-flex justify-content-center">
              <button
                className="w-25 mt-4 mb-4 btn btn-primary"
                onClick={() => saveOnCart(product)}
              >
                Comprar
              </button>
            </div>
          </div>
        </main>
      </>
    );
  };

  return (
    <>{imageRef.current.length > 0 ? tela() : <p>Carregando imagens...</p>}</>
  );
};

export default ProductDetails;
