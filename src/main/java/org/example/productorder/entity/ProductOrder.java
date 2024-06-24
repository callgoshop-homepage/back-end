package org.example.productorder.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.cart.entity.CartItem;
import org.example.global.baseentity.BaseEntity;
import org.example.member.entity.Member;
import org.example.product.entity.Product;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class ProductOrder extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    //    수취인명
    private String receiver;

    //    승인 처리일
    private LocalDateTime approvalDate;

    //    주문 신청일
    private LocalDateTime orderDate;

    //    배송 방법
    private String deliveryMethod;

    //    택배사
    private String parcelCompany;

    //    송장 번호
    private Long invoiceNumber;

    //    승인 상태 0 : 미승인 , 1 : 승인 , 2 : 승인 거절
    private int approveStatus;

    //    주소
    private String address;

    // 총 금액
    private Long totalPrice;

    //    주문 상세 더하는 구문
    @JsonManagedReference
    @OneToMany(mappedBy = "productOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductOrderItem> productOrderItems = new ArrayList<>();

    public void addProductOrderItem(ProductOrderItem item) {
        if (this.productOrderItems == null) {
            this.productOrderItems = new ArrayList<>();
        }
        item.setProductOrder(this);
        this.productOrderItems.add(item);
    }

    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonBackReference
    private Member member;
}
