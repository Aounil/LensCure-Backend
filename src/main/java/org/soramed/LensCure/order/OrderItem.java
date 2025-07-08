package org.soramed.LensCure.order;


import jakarta.persistence.*;
import lombok.*;
import org.soramed.LensCure.product.Product;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    why
    @ManyToOne
    private Product product;

    private int quantity;

    @ManyToOne
    private Order order;




}
