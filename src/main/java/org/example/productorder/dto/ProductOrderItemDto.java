package org.example.productorder.dto;

import lombok.*;

import java.util.List;

@Data
public class ProductOrderItemDto {
    private Long productId;
    private int count;
    private List<ProductOrderItemDto.OptionCount> options;

    @Data
    public static class OptionCount {
        private Long optionId;
        private int count;
    }
}
