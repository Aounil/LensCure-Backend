package org.soramed.LensCure.controllers;


import org.soramed.LensCure.DTO.OrderItemResponse;
import org.soramed.LensCure.DTO.OrderResponse;
import org.soramed.LensCure.User.User;
import org.soramed.LensCure.User.UserRepository;
import org.soramed.LensCure.order.*;
import org.soramed.LensCure.product.Product;
import org.soramed.LensCure.product.ProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ClientController {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;


    public ClientController(ProductRepository productRepository, UserRepository userRepository, OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @GetMapping("/all")
    public Iterable<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    @GetMapping("/orders")
    public List<?> getOrders(@RequestParam String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        User user = optionalUser.get();

        List<Order> orders = orderRepository.findOrderByUserId(user.getId());
        List<OrderItem> allItems = new ArrayList<>();
        List<OrderResponse> response = new ArrayList<>();

        for (Order order : orders) {
            List<OrderItem> items = orderItemRepository.findByOrderId(order.getId());
            // Use DTOs here to avoid exposing full entities,
            // which could cause infinite JSON loops and leak sensitive fields.

            // Map each OrderItem entity to an OrderItemResponse DTO
            List<OrderItemResponse> itemResponses = items.stream()
                    .map(item -> new OrderItemResponse(
                            item.getId(),
                            item.getProduct().getName(),
                            item.getProduct().getPrice(),
                            item.getProduct().getImage_path(),
                            item.getQuantity()
                    ))
                    .collect(Collectors.toList());

            OrderResponse orderResponse = new OrderResponse(order.getId(), order.getCreatedAt(), order.getStatus(), itemResponses);
            response.add(orderResponse);
        }

        return response;
    }


}
