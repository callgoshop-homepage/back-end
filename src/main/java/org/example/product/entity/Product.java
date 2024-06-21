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
    @JsonManagedReference
    private List<Board> boards;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DetailBoard> detailBoards;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<CartItem> cartItems;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ProductOption> productOptions;

    @ManyToOne
    @JoinColumn(name = "productOrder_id")
    @JsonBackReference
    private ProductOrder productOrder;
}
