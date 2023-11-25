package com.example.test.service.imp;

import com.example.test.entity.Cart;
import com.example.test.repository.CartRepository;
import com.example.test.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    public List<Cart> ViewAllCart() {
        List<Cart> cartList = cartRepository.findAll();
        return cartList;
    }

    @Override
    public void deleteAllCart() {
        cartRepository.deleteAll();
    }


}
