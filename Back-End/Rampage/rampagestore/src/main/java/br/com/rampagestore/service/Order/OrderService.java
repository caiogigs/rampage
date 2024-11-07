package br.com.rampagestore.service.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.rampagestore.model.Order.Order;
import br.com.rampagestore.repository.OrderRepository;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;

    public ResponseEntity<?> createOrder(Order order){
        orderRepository.save(order);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
