package org.example.product.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.example.cart.entity.CartItem;
import org.example.global.baseentity.BaseEntity;
import org.example.productorder.entity.ProductOrderItem;

import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductOptionRequest extends BaseEntity {

    private Long id;
    @JsonProperty("name")
    private String optionName;

    @JsonProperty("price")
    private Long optionPrice;

    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
}
