import Carousel from "react-bootstrap/Carousel";
import ImgCarrosel from "../ImgCarrossel/ImgCarrosel";
import React from "react";

const Carrosel = React.memo(({ imageBase64 }) => {
  if (!Array.isArray(imageBase64)) {
    return <p>Sem imagens disponíveis.</p>;
  }
  return (
    <Carousel data-bs-theme="dark">
      {imageBase64.map((image, index) => (
        <Carousel.Item key={index}>
          <ImgCarrosel image64={image} />
          <Carousel.Caption></Carousel.Caption>
        </Carousel.Item>
      ))}
    </Carousel>
  );
});

export default Carrosel;
