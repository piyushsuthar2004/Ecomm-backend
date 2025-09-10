package com.example.ecomm.controller;

import com.example.ecomm.dto.OrderDTO;
import com.example.ecomm.model.OrderRequest;
import com.example.ecomm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Place an order for a user
    @PostMapping("/place/{userId}")
    public OrderDTO placeOrder(@PathVariable Long userId, @RequestBody OrderRequest orderRequest) {
        return orderService.placeOrder(userId, orderRequest.getProductQuantities(), orderRequest.getTotalAmount());
    }

    // Get all orders
    @GetMapping("/all-orders")
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    // Get all orders placed by a specific user
    @GetMapping("/user/{userId}")
    public List<OrderDTO> getOrderByUser(@PathVariable Long userId) {
        return orderService.getOrderByUser(userId);
    }
}
