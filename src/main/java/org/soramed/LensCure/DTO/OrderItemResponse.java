package org.soramed.LensCure.DTO;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponse {

    private int id;
    private String name;
    private double price;
    private String imagePath;
    private int quantity;
}
