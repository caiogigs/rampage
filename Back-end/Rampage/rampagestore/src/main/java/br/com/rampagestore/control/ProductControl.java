package br.com.rampagestore.control;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.rampagestore.model.Product;
import br.com.rampagestore.service.ProductService;


@RestController
@CrossOrigin(origins = "*")
public class ProductControl {

    @Autowired
    private ProductService productService;

    @PutMapping("/alterar")
    public ResponseEntity<?> updateProduct(@RequestBody Product product){
        return productService.registerOrUpdateProducts(product, "update");
    }

    @PostMapping("/cadastrarProduto")
    public ResponseEntity<?> signProduct(@RequestBody Product product){
        return productService.registerOrUpdateProducts(product, "register");
    }

    @GetMapping("/listar")
    public Iterable<Product> catalogProducts(){
        return productService.listingProducts();
    }

    @GetMapping("/")
    public String rote(){
        return "API de produtos funcionando!";
    }

}
