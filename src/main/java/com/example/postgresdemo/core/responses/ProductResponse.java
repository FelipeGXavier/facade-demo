package com.example.postgresdemo.core.responses;

public class ProductResponse {

    private String name;
    private String title;

    public ProductResponse(String name, String title) {
        this.name = name;
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }
}
