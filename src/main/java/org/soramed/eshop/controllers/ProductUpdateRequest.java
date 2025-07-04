package org.soramed.eshop.controllers;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductUpdateRequest {
    private String name;
    private double price;
    private String description;
    private int quantity;
    private String status;
    private String reference;
}
