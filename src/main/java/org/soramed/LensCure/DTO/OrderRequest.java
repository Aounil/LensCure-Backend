package org.soramed.LensCure.DTO;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    private String userEmail;
    private List<ItemDto> items;

    @Data
    public static class ItemDto {
        private int productId;
        private int quantity;
    }
}
