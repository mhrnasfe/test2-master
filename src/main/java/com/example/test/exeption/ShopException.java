package com.example.test.exeption;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ShopException extends RuntimeException{

    private final String message;




}
