package br.com.rampagestore.control;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    //EndPoint para exibir produto na ladingPage
    @GetMapping("/olhar_produto/{id}")
    public ResponseEntity<?> selectProductIbLdPage(@PathVariable Long id){
        return productService.selectProductByUser(id);
    }

    //EndPoint para mostrar todos os produtos ladingPage
    @GetMapping("/todos_produtos")
    public ResponseEntity<?> selectForLandingPage(){
        return productService.selectAllProductsAndImgs();     
    }

    //EndPoint do botão visualisar do BackOffice
    @GetMapping("/selecionar_produto/{id}")
    public ResponseEntity<?> selectProductForModal(@PathVariable Long id){
        return productService.selectProduct(id);
    }

    //EndPoint de registrar produtos 
    @PostMapping("/register_Product")
    public ResponseEntity<?> testUpload(@ModelAttribute ProductObj productObj, @RequestPart("img") List<MultipartFile>img) {        
        return productService.registerNewProduct(productObj, img);
    }

    //EndPoint de atualizar Produtos
    @PutMapping("atualizar_produto")
    public ResponseEntity<?> updateExistProduct(@ModelAttribute ProductObj obj, @RequestPart("img") List<MultipartFile>img){
        obj.setId(Long.valueOf(obj.getId()));
        System.out.println(obj.getId());
        return productService.updateProduct(obj, img);
    }

    //EndPoint para listar todos os Produtos no BackOffice
    @GetMapping("/listar_produto")
    public ResponseEntity<?> catalogProducts(){
        return productService.listingProducts();
    }

    //EndPoint para listar todos os produtos ordenando os mais recentes no BackOffice
    @GetMapping("/listar_produto_recente")
    public ResponseEntity<?> recentProducts(){
        return productService.listingRecentsProducts();
    }


    //EndPoint para pesquisar produtos pela palavra chave no BackOffice
    @GetMapping("/produtos_contem_palavra")
    public Iterable<ProductObj> productContain(@RequestParam String term){
        return productAction.findByProductNameContainingIgnoreCase(term); 
    }


    //EndPoint para alterar o status do produto
    @PutMapping("/mudar_status_produto")
    public ResponseEntity<?> changeProductStatus(@RequestBody ProductObj obj){
        return productService.changeStatus(obj);
    }

    //EndPoint para aumentar quantidade de produtos 
    @PutMapping("/aumentar_quantidade_produto")
    public ResponseEntity<?> upProductAmount(@RequestBody ProductObj obj, @RequestParam int increment){
        return productService.upAmount(obj, increment);
    }

    //EndPoint para diminuir a quantidade de produtos
    @PutMapping("/diminuir_quantidade_produto")
    public ResponseEntity<?> decreaseProductAmount(@RequestBody ProductObj obj, @RequestParam int decrement){
        return productService.downAmount(obj, decrement);
    }

}