package org.soramed.LensCure.order;

public record OrderItemResponse(
        int id,
        String name,
        double price,
        String image_path,
        int quantity
) {}
