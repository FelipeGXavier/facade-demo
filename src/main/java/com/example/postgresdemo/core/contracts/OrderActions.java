package com.example.postgresdemo.core.contracts;

import com.example.postgresdemo.core.requests.OrderCreateRequest;
import com.example.postgresdemo.core.responses.UserOrderResponse;
import com.example.postgresdemo.model.Item;
import com.example.postgresdemo.model.Order;

import java.util.List;

public interface OrderActions {

    void createOrder(OrderCreateRequest order);
    UserOrderResponse findAllOrdersByUser(Long id);
    void cancelAnOrder(Long orderId);


}
