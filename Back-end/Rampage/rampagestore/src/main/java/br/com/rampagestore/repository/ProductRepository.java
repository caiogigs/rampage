package br.com.rampagestore.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.rampagestore.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{
    
    List<Product> findAllByOrderByCreatedAtDesc();

    List<Product> findByProductNameContainingIgnoreCase(String keyword);
    
}
