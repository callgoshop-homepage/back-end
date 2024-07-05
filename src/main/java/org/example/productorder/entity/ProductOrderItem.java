package org.example.productorder.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.global.baseentity.BaseEntity;
import org.example.product.entity.Product;
import org.example.product.entity.ProductOption;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class ProductOrderItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private int count;

    private String productName;

    private String optionName;

    private Long optionPrice;

//    @ManyToOne
//    @JoinColumn(name = "product_option_id")
//    @JsonBackReference
//    private ProductOption productOption;

//    @ManyToOne
//    @JoinColumn(name = "product_id")
//    @JsonBackReference
//    private Product product;

    @ManyToOne
    @JoinColumn(name = "product_order_id")
    @JsonBackReference
    private ProductOrder productOrder;

//    public void addProductOption(ProductOption productOption) {
//        this.productOption = productOption;
//        if (productOption != null && !productOption.getProductOrderItems().contains(this)) {
//            productOption.addProductOrderItem(this);
//        }
//    }

//    public void addProduct(Product product) {
//        this.product = product;
//        if (product != null && !product.getProductOrderItems().contains(this)) {
//            product.addProductOrderItem(this);
//        }
//    }
}
