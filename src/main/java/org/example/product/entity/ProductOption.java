package org.example.product.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.cart.entity.CartItem;
import org.example.global.baseentity.BaseEntity;
import org.example.productorder.entity.ProductOrderItem;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class ProductOption extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

//    옵션 이름
    private String optionName;

//    옵션 가격
    private Long optionPrice;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product product;

    @OneToMany(mappedBy = "productOption", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<CartItem> cartItems;

//    @OneToMany(mappedBy = "productOption", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference
//    private List<ProductOrderItem> productOrderItems = new ArrayList<>();
//
//    public void addProductOrderItem(ProductOrderItem productOrderItem) {
//        if (this.productOrderItems == null) {
//            this.productOrderItems = new ArrayList<>();
//        }
//        if (!this.productOrderItems.contains(productOrderItem)) {
//            this.productOrderItems.add(productOrderItem);
//            productOrderItem.setProductOption(this);
//        }
//    }
}
