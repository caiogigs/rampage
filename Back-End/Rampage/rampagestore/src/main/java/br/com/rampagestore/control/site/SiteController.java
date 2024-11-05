package br.com.rampagestore.control.site;

import br.com.rampagestore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/site")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SiteController {

    @Autowired
    private ProductService productService;

    //EndPoint para mostrar todos os produtos ladingPage
    @GetMapping("/todos_produtos")
    public ResponseEntity<?> selectForLandingPage(){
        return productService.selectAllProductsAndImgs();
    }

    @GetMapping("/product-by-id")
    public ResponseEntity<?> listingProductById(@RequestParam(name = "id") Long id) {
        return productService.listingProductById(id);
    }

}
