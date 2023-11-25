package com.example.test.dto;

import com.example.test.entity.Address;
import com.example.test.entity.Product;
import lombok.Data;

import java.util.List;

@Data
public class ChangingTheOrderByTheCustomer {

    private Long id;
    private String phoneNumber;
    private Address address;
    private List<Product> productName;
}