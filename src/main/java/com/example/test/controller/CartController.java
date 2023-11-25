package com.example.test.controller;

import com.example.test.entity.Cart;
import com.example.test.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @GetMapping("/findAllCart")
    public ResponseEntity<List<Cart>> getAllProducts() {

        List<Cart> list = cartService.ViewAllCart();

        return new ResponseEntity<List<Cart>>(list, HttpStatus.OK);
    }
    @DeleteMapping(value = "/deleteCart")
    public void deleteCartHandler(){
         cartService.deleteAllCart();
    }
}
