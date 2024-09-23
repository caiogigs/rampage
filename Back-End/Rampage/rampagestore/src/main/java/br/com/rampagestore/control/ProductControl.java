package br.com.rampagestore.control;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.rampagestore.model.ProductObj;
import br.com.rampagestore.repository.ProductRepository;
import br.com.rampagestore.service.ProductService;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductControl {

    @Autowired
    private ProductRepository productAction;

    @Autowired
    private ProductService productService;

    //EndPoint atualizado de cadastro de Produto
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/cadastrar_novo_produto")
    public ResponseEntity<?> signNewProduct(@RequestBody ProductObj obj){
        return productService.registerNewProduct(obj);
    }

    //EndPoint atualizado de atualizar Produto
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/atualizar_produto")
    public ResponseEntity<?> updateExistProduct(@RequestBody ProductObj obj){
        return productService.updateProduct(obj);
    }

    //EndPoint para listar todos os Produtos
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/listarProduto")
    public ResponseEntity<?> catalogProducts(){
        return productService.listingProducts();
    }

    //EndPoint para listar todos os Ordenando os mais recentes Produtos
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/listar_produto_recente")
    public ResponseEntity<?> recentProducts(){
        return productService.listingRecentsProducts();
    }


    //EndPoint para pesquisar produtos pela palavra chave
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/produtos_contem_palavra")
    public Iterable<ProductObj> productContain(@RequestParam String term){
        return productAction.findByProductNameContainingIgnoreCase(term); 
    }


    //EndPoint para alterar o status do produto
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/mudar_status_produto")
    public ResponseEntity<?> changeProductStatus(@RequestBody ProductObj obj){
        return productService.changeStatus(obj);
    }

    //EndPoint para aumentar quantidade de produtos 
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/aumentar_quantidade_produto")
    public ResponseEntity<?> upProductAmount(@RequestBody ProductObj obj, @RequestParam int increment){
        return productService.upAmount(obj, increment);
    }

    //EndPoint para diminuir a quantidade de produtos
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/diminuir_quantidade_produto")
    public ResponseEntity<?> decreaseProductAmount(@RequestBody ProductObj obj, @RequestParam int decrement){
        return productService.downAmount(obj, decrement);
    }

}