package com.example.postgresdemo.core.requests;

import com.example.postgresdemo.model.Product;

public class ItemCreateRequest {

    private int quantity;
    private Product product;

    public ItemCreateRequest(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }
}
