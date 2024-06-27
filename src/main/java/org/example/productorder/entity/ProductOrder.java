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

    //    주문 상태
    private String orderStatus;

    //    수취인명
    private String reciever;

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

    //    승인 상태
    private String approveStatus;

    //    주소
    private String address;

    //    총 금액
    private Long totalPrice;

    //    구매자 명
    private String buyer;

    //    구매자 전화번호
    private String buyerPhoneNumber;

    //    상품 명
    private String productName;

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

    //     (fetch = FetchType.EAGER) jpa에서 관계매핑되어있는 데이터 정보 조회할 수 있게 해주는 구문
    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonBackReference
    private Member member;

    public void addMember(Member member) {
        this.member = member;
        if (member != null && !member.getProductOrders().contains(this)) {
            member.addProductOrder(this);
        }
    }
}
