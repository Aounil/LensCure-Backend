package org.soramed.LensCure.order;

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
