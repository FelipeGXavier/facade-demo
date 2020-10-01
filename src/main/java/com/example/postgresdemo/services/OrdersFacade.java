package com.example.postgresdemo.services;

import com.example.postgresdemo.core.contracts.OrderActions;
import com.example.postgresdemo.core.requests.OrderCreateRequest;
import com.example.postgresdemo.core.responses.ItemResponse;
import com.example.postgresdemo.core.responses.ProductResponse;
import com.example.postgresdemo.core.responses.UserOrderResponse;
import com.example.postgresdemo.exception.PlaceOrderException;
import com.example.postgresdemo.model.Item;
import com.example.postgresdemo.model.Order;
import com.example.postgresdemo.repository.ItemRepository;
import com.example.postgresdemo.repository.OrderRepository;
import com.example.postgresdemo.repository.ProductRepository;
import com.example.postgresdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrdersFacade implements OrderActions {

    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrdersFacade(ItemRepository itemRepository,
                        OrderRepository orderRepository,
                        UserRepository userRepository,
                        ProductRepository productRepository) {
        this.itemRepository = itemRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void createOrder(OrderCreateRequest order) {
        OrderValidation orderValidation = new OrderValidation(this.productRepository, order);
        if (orderValidation.validate()) {
            Order incomingOrder = new Order();
            incomingOrder.setUser(order.getUser());
            this.orderRepository.save(incomingOrder);
            order.getItems().forEach(item -> {
                item.setOrder(incomingOrder);
                itemRepository.save(item);
            });
        }else{
            throw new PlaceOrderException(orderValidation.getErrorMessages());
        }

    }

    @Override
    public UserOrderResponse findAllOrdersByUser(Long id) {
        return this.mapToOrderResponse(this.orderRepository.findAllItemsByOrder(id));
    }

    @Override
    public void cancelAnOrder(Long orderId) {
        if(this.orderRepository.findById(orderId).isPresent()){
            this.orderRepository.cancelOrder(orderId);
        }else{
            throw new RuntimeException("Order not found");
        }
    }


    private UserOrderResponse mapToOrderResponse(List<Item> items) {
        List<ItemResponse> newItems = new ArrayList<>();
        items.forEach(item -> {
            newItems.add(
                    new ItemResponse(
                            new ProductResponse(
                                    item.getProduct().getName(),
                                    item.getProduct().getIdentifier()),
                            item.getQuantity()
                    )
            );
        });
        return new UserOrderResponse(newItems);
    }


}
