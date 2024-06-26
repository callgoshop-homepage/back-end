package org.example.cart.dto;

import lombok.*;
import org.example.global.baseentity.BaseEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemDto extends BaseEntity {

    private Long id;
    private Long productId;
    private String productName;
    private double productPrice;
    private int count;
    private double totalPrice;
    private String optionName;
    private double optionPrice;

}
