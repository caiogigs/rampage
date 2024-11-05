import { Link, useLocation } from "react-router-dom";
import productService from "../../../Services/ProductService/ProductService";
import "./ProductDetails.css";
import Carrosel from "../../../Components/Carousel/Carousel";
import { useEffect, useRef, useState } from "react";
import StarRating from "../../../Components/StarsRating/StarRating";

const ProductDetails = () => {
  const location = useLocation();
  const queryParams = new URLSearchParams(location.search);
  const productId = queryParams.get("product");

  const [product, setProduct] = useState({});
  const imageRef = useRef([]);

  useEffect(() => {
    const fetchProduct = async () => {
      try {
        const data = await productService.getProductByID(productId);

        if (data?.product) setProduct(data.product);

        if (data?.imageBase64) imageRef.current = data.imageBase64;

        console.log("img  ", data);
      } catch (error) {
        console.error("Error fetching product data:", error);
      }
    };

    if (productId) {
      fetchProduct();
    }
  }, [productId]);

  return (
    <>
      <main className="w-100 h-100 mt-4">
        <div className="w-100">
          <div className="row">
            <div className="col-6">
              {imageRef.current.length > 0 ? (
                <Carrosel imageBase64={imageRef.current} />
              ) : (
                <p>Carregando imagens...</p>
              )}
            </div>

            <div className="col-6">
              <div>
                <StarRating className="rating" avaliacao={product.avaliation} />
                <p>Produto: {product.productName}</p>
                <p>Detalhes: </p>
                <textarea>{product.productDetai}</textarea>
                <p>Preço: R$ {product.productPrice}</p>
                <p>Quatidade Disponível: {product.amount}</p>
              </div>
            </div>
          </div>
          <div className="row ">
            <Link className="w-100 d-flex justify-content-center" to={"/cart"}>
              <button className="w-25 mt-4 mb-4 btn btn-primary">
                Comprar
              </button>
            </Link>
          </div>
        </div>
      </main>
    </>
  );
};

export default ProductDetails;
