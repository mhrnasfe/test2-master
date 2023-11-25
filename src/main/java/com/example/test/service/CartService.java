package com.example.test.service;

import com.example.test.entity.Cart;

import java.util.List;

public interface CartService {

    List<Cart> ViewAllCart();

    void deleteAllCart();
}
