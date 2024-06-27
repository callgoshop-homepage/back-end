package org.example.product.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.board.entity.Board;
import org.example.board.entity.DetailBoard;
import org.example.cart.entity.CartItem;
import org.example.global.baseentity.BaseEntity;
import org.example.cart.entity.Cart;
import org.example.productorder.entity.ProductOrder;
import org.example.productorder.entity.ProductOrderItem;
import org.example.suggestion.entity.Suggestion;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String productName;

    private Long price;

    private Long productNumber;

    //    상품 종류
    private String type;

    //    택배사
    private String parcel;

    //    @JsonManagedReference는 순환참조 에러를 해결하기 위한 방법
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("product-boards")
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("product-detailBoards")
    private List<DetailBoard> detailBoards = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("product-cartItems")
    private List<CartItem> cartItems = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("product-productOptions")
    private List<ProductOption> productOptions = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("product-productOrderItems")
    private List<ProductOrderItem> productOrderItems = new ArrayList<>();

    public void addProductOrderItem(ProductOrderItem productOrderItem) {
        if (this.productOrderItems == null) {
            this.productOrderItems = new ArrayList<>();
        }
        if (!this.productOrderItems.contains(productOrderItem)) {
            this.productOrderItems.add(productOrderItem);
            productOrderItem.setProduct(this);
        }
    }

    @ManyToOne
    @JoinColumn(name = "suggestion_id")
    @JsonBackReference
    private Suggestion suggestion;

    public void addSuggestion(Suggestion suggestion) {
        this.suggestion = suggestion;
    }
}
