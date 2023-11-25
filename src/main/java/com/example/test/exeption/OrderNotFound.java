package com.example.test.exeption;

public class OrderNotFound extends ShopException {

    public OrderNotFound(String message) {
        super(message);
    }
}
