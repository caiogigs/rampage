package br.com.rampagestore.control.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.rampagestore.model.Order.Order;
import br.com.rampagestore.service.Order.OrderService;

@RestController
@RequestMapping("/rampage/pedido")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/novo_pedido")
    public ResponseEntity<?> newOrder(@RequestBody Order order){
            return orderService.createOrder(order);
    }

    @GetMapping("/get-my-orders")
    public ResponseEntity<?> selectOrdersByIdClient(@RequestParam(name = "id") Long userId){
        return orderService.selectOrdersByIdClient(userId);
    }
}
