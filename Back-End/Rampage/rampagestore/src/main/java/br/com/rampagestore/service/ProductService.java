package br.com.rampagestore.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.rampagestore.model.ImageModel;
import br.com.rampagestore.model.ModelMessage;
import br.com.rampagestore.model.ProductObj;
import br.com.rampagestore.repository.ImageRepository;
import br.com.rampagestore.repository.ProductRepository;

//Classe service Responsável por lidar com as regras de negocio
@Service
public class ProductService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ProductRepository productAction;

    @Autowired
    private ModelMessage message;

    @Autowired
    private ImageService imageService;

    // Método para listar todos os produtos
    public ResponseEntity<?> listingProducts() {
        List<ProductObj> prod = productAction.findAll();
        return new ResponseEntity<>(prod, HttpStatus.OK);
    }

    // Método para listar todos os produtos ordenando do mais recente
    public ResponseEntity<?> listingRecentsProducts() {
        List<ProductObj> prod = productAction.findAllByOrderByCreatedAtDesc();
        return new ResponseEntity<>(prod, HttpStatus.OK);
    }

    // Método para cadastrar Produtos
    public ResponseEntity<?> registerNewProduct(ProductObj obj, MultipartFile mainImage, MultipartFile[] images) {
        ProductObj existingProduct = productAction.findByProductName(obj.getProductName());

        if (existingProduct != null) {
            message.setMessage("Tentativa de cadastro invalida. " +
                    "O produto: " + obj.getProductName() + ", já está cadastrado. " +
                    "Verifique a Lista de Produtos e/ou as informações fornecidas e tente novamente.");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else {
            ProductObj productObj = new ProductObj(obj.getProductName(), obj.getProductDetai(),
            obj.getProductPrice(), obj.getAvaliation(), true, obj.getAmount());
            
            productAction.save(productObj);
            Long productId = productObj.getId();

            try {
                saveImages(productId, mainImage, images);
            } catch (IOException e) {
                message.setMessage("Erro ao salvar imagens: " + e.getMessage());
                return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            message.setMessage("Produto: " + productObj.getProductName() + ", Cadastrado com sucesso! " +
                    "Verifique a lista de produtos.");
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        }
    }



    public void saveImages(long productId, MultipartFile mainImage, MultipartFile[] images) throws IOException {
        String directionMainImg = imageService.saveImage(mainImage);
        ImageModel mainImageModel = new ImageModel(productId, mainImage.getOriginalFilename(), directionMainImg, true,
        true);
        System.out.println(mainImage.getOriginalFilename());
        imageRepository.save(mainImageModel);
        for (int i = 0; i <images.length; i++) {
            String directionImgs = imageService.saveImage(images[i]);
            System.out.println("AAAAAAAa");
            ImageModel imageModel = new ImageModel(productId, images[i].getOriginalFilename(), directionImgs, true, true);
            imageRepository.save(imageModel);
        }
    }

    // Método para atualizar Produtos
    public ResponseEntity<?> updateProduct(ProductObj obj) {
        ProductObj existingProduct = productAction.findById(obj.getId());
        if (existingProduct != null) {
            ProductObj productByName = productAction.findByProductName(obj.getProductName());
            // Verifica se existe outro produto com o mesmo nome e ID diferente
            if (productByName != null && !Objects.equals(productByName.getId(), obj.getId())) {
                message.setMessage("Já existe um produto cadastrado com este nome.");
                return new ResponseEntity<>(message, HttpStatus.NOT_MODIFIED);
            } else {
                // Atualiza o produto
                ProductObj updatedProduct = new ProductObj(
                        existingProduct.getId(),
                        obj.getProductName(),
                        obj.getProductDetai(),
                        obj.getProductPrice(),
                        obj.getAvaliation(),
                        true,
                        obj.getAmount(),
                        LocalDateTime.now());
                message.setMessage("Produto atualizado De: " + existingProduct + ". Para: " + updatedProduct);
                productAction.save(updatedProduct);
                return new ResponseEntity<>(message, HttpStatus.OK);
            }
        } else {
            message.setMessage(
                    "Tentativa de Atualização inválida. Produto: " + obj.getProductName() + ", não cadastrado.");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    // Método para aumentar a quantidade de produtos
    public ResponseEntity<?> upAmount(ProductObj productObj, int increment) {
        ProductObj productUp = productAction.findById(productObj.getId());
        productUp.setAmount(productObj.getAmount() + increment);
        productAction.save(productUp);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Método para diminuir a quantidade de produtos
    public ResponseEntity<?> downAmount(ProductObj productObj, int decrement) {
        ProductObj productDown = productAction.findById(productObj.getId());
        if (productDown.getAmount() <= 0) {
            message.setMessage("Quantidade solicitada indisponível no estoque." +
                    " Por favor, ajuste a quantidade e tente novamente.");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else {
            int newAmount = productDown.getAmount() - decrement;
            if (newAmount < 0) {
                message.setMessage("Temos apenas " + productDown.getAmount()
                        + " unidades de " + productDown.getProductName()
                        + " em estoque. Por favor, ajuste a quantidade para prosseguir.");
                return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
            }
            productDown.setAmount(newAmount);
            productAction.save(productDown);
            message.setMessage("Quantidade atualizada com sucesso.");
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }

    // Método para mudar o status do produto
    public ResponseEntity<?> changeStatus(ProductObj obj) {
        ProductObj changeProductObj = productAction.findById(obj.getId());
        changeProductObj.setStatus(!changeProductObj.isStatus());
        productAction.save(changeProductObj);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
