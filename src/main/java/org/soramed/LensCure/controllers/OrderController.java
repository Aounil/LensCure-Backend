package org.soramed.LensCure.controllers;
import org.soramed.LensCure.DTO.OrderRequest;
import org.soramed.LensCure.order.*;
import lombok.RequiredArgsConstructor;
import org.soramed.LensCure.User.User;
import org.soramed.LensCure.User.UserRepository;
import org.soramed.LensCure.product.Product;
import org.soramed.LensCure.product.ProductRepository;
import org.soramed.LensCure.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final EmailService emailService;

    @PostMapping
    public ResponseEntity<?> placeOrder(@RequestBody OrderRequest request) {

        Optional<User> userOpt = userRepository.findByEmail(request.getUserEmail());
        if (userOpt.isEmpty()) return ResponseEntity.badRequest().body("Invalid user ID");

        User user = userOpt.get();

        Order order = new Order();
        order.setUser(user);
        order.setCreatedAt(LocalDateTime.now());

        List<OrderItem> itemList = new ArrayList<>();
        for (OrderRequest.ItemDto itemDto : request.getItems()) {
            Optional<Product> productOpt = productRepository.findById(itemDto.getProductId());
            if (productOpt.isEmpty()) continue;

            OrderItem item = new OrderItem();
            item.setProduct(productOpt.get());
            item.setQuantity(itemDto.getQuantity());
            item.setOrder(order);

            itemList.add(item);
        }

        order.setItems(itemList);
        orderRepository.save(order);

        StringBuilder productNames = new StringBuilder();
        for (OrderItem item : itemList) {
            productNames.append("- ")
                    .append(item.getProduct().getName())
                    .append(" (Qty: ")
                    .append(item.getQuantity())
                    .append(")\n");
        }

        String emailBody = String.format(
                "Hello %s,\n\nThank you for your order!\n\n" +
                        "You have ordered %d item(s):\n%s\n" +
                        "We are processing your order and will notify you once it is shipped.\n\n" +
                        "Best regards,\nLensCure Team",
                user.getName(),
                itemList.size(),
                productNames.toString()
        );

        emailService.sendEmail(
                user.getEmail(),
                "Thank you for your order with LensCure",
                emailBody
        );

        return ResponseEntity.ok("Order placed successfully");
    }
}
