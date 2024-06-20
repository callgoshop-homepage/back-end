package org.example.product.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProductOptionRequest {
    @JsonProperty("name")
    private String optionName;

    @JsonProperty("price")
    private Long optionPrice;
}
