package org.example.productorder.dto;

import lombok.*;

import java.util.List;

@Data
public class ProductOrderItemDto {
    private Long productId;
    private String productName;
    private int count;
    private List<OptionCount> options;

    @Data
    public static class OptionCount {
        private Long optionId;
        private String optionName;
        private Long optionPrice;
        private int count;
    }
}
