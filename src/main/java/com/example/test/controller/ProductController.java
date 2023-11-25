package com.example.test.controller;

import com.example.test.Enum.Category;
import com.example.test.dto.ProductDto;
import com.example.test.entity.Product;
import com.example.test.exeption.AllReadyExist;
import com.example.test.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/createProduct")
    public ResponseEntity<Product> create(@RequestBody ProductDto product) throws AllReadyExist {
        Product newProduct = productService.addProducts(product);
        return new ResponseEntity<>(newProduct,HttpStatus.CREATED);
//        return ResponseEntity.ok(productService.addProducts(product));
    }
    @GetMapping("/findAllProduct")
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productService.all());
    }
    @GetMapping("/getProduct/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        Product product =  productService.getProductById(id);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }
    @GetMapping("/getCategoryProducts/{category}")
    public List<ProductDto> CategoryProducts(@PathVariable("category") Category category) {
        return productService.findByCategory(category);
    }
    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody ProductDto product, @PathVariable Long id) {
        Product updateProduct = productService.updateProduct(product, id);
        return ResponseEntity.status(HttpStatus.OK).body(updateProduct);
    }
    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
