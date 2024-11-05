import Carousel from "react-bootstrap/Carousel";
import ImgCarrosel from "../ImgCarrossel/ImgCarrosel";

function Carrosel({imageBase64}) {
    console.log("PORRAAAAAAA", imageBase64);
    if (!Array.isArray(imageBase64)) {
        return <p>Sem imagens disponíveis.</p>;
      }
  return (
    <Carousel data-bs-theme="dark">
      {imageBase64.map((image, index) => (
        <Carousel.Item>
          <ImgCarrosel image64={image} />
          <Carousel.Caption></Carousel.Caption>
        </Carousel.Item>
      ))}
    </Carousel>
  );
}

export default Carrosel;

