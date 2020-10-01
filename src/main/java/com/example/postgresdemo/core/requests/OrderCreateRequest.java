package com.example.postgresdemo.core.requests;

import com.example.postgresdemo.model.Item;
import com.example.postgresdemo.model.User;

import java.util.List;

public class OrderCreateRequest {

    private List<Item> items;
    private User user;

    public OrderCreateRequest(List<Item> items, User user) {
        this.items = items;
        this.user = user;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
