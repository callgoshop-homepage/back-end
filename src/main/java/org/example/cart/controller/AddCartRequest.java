package org.example.cart.controller;

import lombok.Data;

import java.util.List;

@Data
public class AddCartRequest {
    public Long productId;
    private int count;
    private List<AddCartRequest.OptionCount> options;

    @Data
    public static class OptionCount {
        private Long optionId;
        private int count;
    }
}
