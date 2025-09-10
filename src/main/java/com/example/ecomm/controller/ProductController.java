package com.example.ecomm.controller;


import com.example.ecomm.model.Product;

import com.example.ecomm.repo.ProductRepo;
import com.example.ecomm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProduct(){
        return productService.getAllProduct();
    }
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }
    @PostMapping
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }
    @DeleteMapping("/{id}")
    public void deleteproduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }
}
