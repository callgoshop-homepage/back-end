package org.example.cart.entity;

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
public class CartItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    @JsonBackReference
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product product;

    @ManyToOne
    @JoinColumn(name = "product_option_id")
    @JsonBackReference
    private ProductOption productOption;

    @Min(value = 1, message = "최소 1개 이상 담아주세요")
    private int count;

    public void addCount(int count){
        this.count += count;
    }
}
