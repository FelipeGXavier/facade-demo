package com.example.postgresdemo.core.responses;

public class ItemResponse {

    private ProductResponse product;
    private int quantity;

    public ItemResponse(ProductResponse product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public ProductResponse getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}
