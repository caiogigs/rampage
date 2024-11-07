package br.com.rampagestore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.rampagestore.model.Order.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long>{
    
}
