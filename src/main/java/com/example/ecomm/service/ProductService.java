package com.example.ecomm.service;

import com.example.ecomm.model.Product;
import com.example.ecomm.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;


    public List<Product> getAllProduct() {
        return productRepo.findAll();
    }


    public Product getProductById(Long id) {
        return productRepo.findById(id).orElse(null);
    }


    public Product addProduct(Product product) {
        return productRepo.save(product);
    }


    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }
}
