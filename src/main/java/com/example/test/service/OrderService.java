package com.example.test.service;

import com.example.test.dto.ChangingTheOrderByTheCustomer;
import com.example.test.entity.Order;

import java.util.ArrayList;
import java.util.List;

public interface OrderService {
    Order createOrder(Order order);

    Order addOrderFromCart(Long customerId);

    List<Order> viewOrder();
    ArrayList<Order> findOrderByUserName(String firstName, String lastName, String mobileNo);

    Order getById(Long id);

    Order update(ChangingTheOrderByTheCustomer customer);

    Order updateOrder(Order order, Long id);

    void deleteOrder(Long customerId);


}
