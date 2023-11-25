package com.example.test.service;

import com.example.test.Enum.Category;
import com.example.test.dto.ProductDto;
import com.example.test.entity.Product;
import com.example.test.exeption.AllReadyExist;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {
    public Product addProducts(ProductDto product) throws AllReadyExist;

    public List<Product> all();

//    public List<ProductDto> findByCategoryWiseProduct(Category category);
    public List<ProductDto> findByCategory(Category category);

    public void deleteProduct(Long id);

    Product updateProduct(ProductDto product, Long id);

    Product getProductById(Long id);


    //    public Products getProductsFromCatalogById(Integer id);
    //    public List<Product> getProductId();
    //    public Product updateProductIncatalog(Product product);
}
