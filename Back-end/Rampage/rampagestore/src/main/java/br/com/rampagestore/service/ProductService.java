package br.com.rampagestore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.rampagestore.model.ModelMessage;
import br.com.rampagestore.model.Product;
import br.com.rampagestore.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ModelMessage modelMessage;
    
    @Autowired
    private ProductRepository productAction;  

    public Iterable<Product> listingProducts(){
        return productAction.findAll();
    }

    public ResponseEntity<?> registerOrUpdateProducts(Product product, String action) {
        if(product.getProductName() == ""){
            modelMessage.setMessage("O nome do produto é obrigatório");
            return new ResponseEntity<ModelMessage>(modelMessage, HttpStatus.BAD_REQUEST);
        }else if(product.getProductBrand() == ""){
            modelMessage.setMessage("O nome da marca é obrigatório");
            return new ResponseEntity<ModelMessage>(modelMessage, HttpStatus.BAD_REQUEST);
        }else{
            if(action.equals("register")){
                return new ResponseEntity<Product>(productAction.save(product), HttpStatus.CREATED);
            }else{
                return new ResponseEntity<Product>(productAction.save(product), HttpStatus.OK);
            }
            
        }
    }
    
     
}
