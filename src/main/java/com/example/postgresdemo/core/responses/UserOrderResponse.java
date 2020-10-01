package com.example.postgresdemo.core.responses;

import java.util.List;

public class UserOrderResponse {

    private List<ItemResponse> items;

    public UserOrderResponse(List<ItemResponse> items) {
        this.items = items;
    }

    public List<ItemResponse> getItems() {
        return items;
    }
}
