package org.soramed.LensCure.controllers;


import org.soramed.LensCure.User.User;
import org.soramed.LensCure.User.UserRepository;
import org.soramed.LensCure.order.Order;
import org.soramed.LensCure.order.OrderItem;
import org.soramed.LensCure.order.OrderItemRepository;
import org.soramed.LensCure.order.OrderRepository;
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

        for (Order order : orders) {
            List<OrderItem> items = orderItemRepository.findByOrderId(order.getId());
            allItems.addAll(items);
        }
//        You start with List<OrderItem>.
//        Each OrderItem points to a Product.
//        You want List<Product>.
//        So you map each item → product, then collect them into a new list.
//
                //  Convert the list of all OrderItems to a Stream to perform functional operations.
        return allItems.stream()
                // For each OrderItem in the stream, map it to its associated Product.
                .map(item -> item.getProduct())
                // Gather all the mapped Products back into a new List.
                .collect(Collectors.toList());


    }


}
