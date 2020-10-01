package com.example.postgresdemo.resources;

import com.example.postgresdemo.core.requests.OrderCreateRequest;
import com.example.postgresdemo.core.responses.UserOrderResponse;
import com.example.postgresdemo.model.Item;
import com.example.postgresdemo.model.Order;
import com.example.postgresdemo.repository.OrderRepository;
import com.example.postgresdemo.services.OrdersFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1/order")
@RestController
public class OrderResource {

    private OrdersFacade ordersFacade;

    @Autowired
    public OrderResource(OrdersFacade ordersFacade) {
        this.ordersFacade = ordersFacade;
    }

    @PostMapping
    public ResponseEntity<?> placeOrder(@RequestBody OrderCreateRequest order){
        this.ordersFacade.createOrder(order);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{order}")
    public ResponseEntity<UserOrderResponse> findOrdersFromId(@PathVariable("order") Long order){
        return new ResponseEntity<>(this.ordersFacade.findAllOrdersByUser(order), HttpStatus.OK);
    }

    @PutMapping("/{order}")
    public ResponseEntity<?> cancelOrder(@PathVariable("order") Long order){
        this.ordersFacade.cancelAnOrder(order);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
