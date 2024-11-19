package br.com.rampagestore.service.Order;

import br.com.rampagestore.model.enums.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.rampagestore.model.Order.Order;
import br.com.rampagestore.repository.OrderRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public ResponseEntity<?> createOrder(Order order) {
        order.setOrderStatus(OrderStatus.AGUARDANDO_PAGAMENTO);
        order.setDateOrdered(LocalDate.now());
        Order objReturn = orderRepository.save(order);
        return new ResponseEntity<>(objReturn, HttpStatus.CREATED);
    }

    public ResponseEntity<?> selectOrdersByIdClient(Long userId) {
        List<Order> orders = orderRepository.findAllByConsumerIdOrderByIdDesc(userId);
        return ResponseEntity.ok(orders);
    }
}
