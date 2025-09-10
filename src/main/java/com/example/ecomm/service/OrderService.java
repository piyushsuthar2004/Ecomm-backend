package com.example.ecomm.service;

import com.example.ecomm.dto.OrderDTO;
import com.example.ecomm.dto.OrderItemDTO;
import com.example.ecomm.model.OrderItem;
import com.example.ecomm.model.Orders;
import com.example.ecomm.model.Product;
import com.example.ecomm.model.User;
import com.example.ecomm.repo.OrderRepo;
import com.example.ecomm.repo.ProductRepo;
import com.example.ecomm.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private OrderRepo orderRepo;

    public OrderService() {
    }

    public OrderDTO placeOrder(Long userId, Map<Long, Integer> productQuantities, double totalAmount) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Orders order = new Orders();
        order.setUser(user);
        order.setOrderDate(new Date());
        order.setStatus("pending");
        order.setTotalAmount(totalAmount);

        List<OrderItem> orderItems = new ArrayList<>();
        List<OrderItemDTO> orderItemDTOS = new ArrayList<>();

        for (Map.Entry<Long, Integer> entry : productQuantities.entrySet()) {
            Product product = productRepo.findById(entry.getKey())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(entry.getValue());

            orderItems.add(orderItem);

            orderItemDTOS.add(new OrderItemDTO(
                    product.getName(),
                    product.getPrice(),
                    entry.getValue()));
        }

        order.setOrderItems(orderItems);
        Orders savedOrder = orderRepo.save(order);

        return new OrderDTO(
                savedOrder.getId(),
                savedOrder.getTotalAmount(),
                savedOrder.getStatus(),
                savedOrder.getOrderDate(),
                order.getUser().getName(),
                order.getUser().getEmail(),
                orderItemDTOS
        );
    }

    public List<OrderDTO> getAllOrders() {
        List<Orders> orders = orderRepo.findAllOrdersWithUsers();
        return orders.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private OrderDTO convertToDTO(Orders order) {
        List<OrderItemDTO> itemDTOList = order.getOrderItems().stream()
                .map(item -> new OrderItemDTO(
                        item.getProduct().getName(),
                        item.getProduct().getPrice(),
                        item.getQuantity()))
                .collect(Collectors.toList());

        return new OrderDTO(
                order.getId(),
                order.getTotalAmount(),
                order.getStatus(),
                order.getOrderDate(),
                order.getUser() != null ? order.getUser().getName() : "Unknown",
                order.getUser() != null ? order.getUser().getEmail() : "Unknown",
                itemDTOList
        );
    }

    public List<OrderDTO> getOrderByUser(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Orders> ordersList = orderRepo.findByUser((org.apache.catalina.User) user);
        return ordersList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
}
