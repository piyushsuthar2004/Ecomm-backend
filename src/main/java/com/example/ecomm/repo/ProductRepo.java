package com.example.ecomm.repo;// In your ProductRepo.java file

// ... other imports
import com.example.ecomm.model.User; // Make sure this import is correct
import com.example.ecomm.model.Product; // Import your Product entity
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> findByUser(User user);
}