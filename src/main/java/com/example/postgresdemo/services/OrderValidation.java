package com.example.postgresdemo.services;

import com.example.postgresdemo.core.requests.OrderCreateRequest;
import com.example.postgresdemo.model.Item;
import com.example.postgresdemo.model.Order;
import com.example.postgresdemo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderValidation {

    private OrderCreateRequest order;
    private List<String> errorMessages = new ArrayList<>();
    private ProductRepository productRepository;

    public OrderValidation(ProductRepository productRepository, OrderCreateRequest order) {
        this.productRepository = productRepository;
        this.order = order;
    }

    public boolean validate() {
        if (this.order.getItems().size() < 1) {
            this.errorMessages.add("The order must have one product at least");
        }
        Set<Long> uniqueProductsId = this.filterUniqueItems();
        if (uniqueProductsId.isEmpty()) {
            this.errorMessages.add("Duplicate products in order");
        } else {
            if (!this.productItemsExists(uniqueProductsId)) {
                this.errorMessages.add("Product not found");
            }
        }
        if (this.errorMessages.isEmpty()) {
            return true;
        }
        return false;
    }

    private Set<Long> filterUniqueItems() {
        List<Long> itemsId = this.order.getItems().stream().map(item -> item.getProduct().getId()).collect(Collectors.toList());
        Set<Long> uniqueItemsId = new HashSet<>(itemsId);
        if (uniqueItemsId.size() != itemsId.size()) {
            return new HashSet<>();
        }
        return uniqueItemsId;
    }

    private boolean productItemsExists(Set<Long> items) {
        int size = items.size();
        List<Long> itemsId = this.productRepository.findIdInRanges(items);
        if (itemsId.size() != size) {
            return false;
        }
        return true;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }
}
