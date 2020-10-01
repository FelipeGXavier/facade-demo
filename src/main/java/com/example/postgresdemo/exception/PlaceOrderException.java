package com.example.postgresdemo.exception;

import java.util.List;

public class PlaceOrderException extends RuntimeException {

    private List<String> messages;

    public PlaceOrderException(List<String> messages) {
        this.messages = messages;
    }

    @Override
    public String getMessage() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String message : this.messages) {
            stringBuilder.append(message);
        }
        return stringBuilder.toString();
    }
}
