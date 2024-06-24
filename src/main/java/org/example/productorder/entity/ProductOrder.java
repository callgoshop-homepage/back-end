package org.example.productorder.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.global.baseentity.BaseEntity;
import org.example.member.entity.Member;
import org.example.product.entity.Product;

import java.time.LocalDateTime;

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

    //    승인 상태
    private int approveStatus;

    //    주소
    private String address;

    //    수량
    @Min(value = 1, message = "최소 1개 이상 담아주세요")
    private int count;

    // 총 금액
    private Long totalPrice;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product product;

    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonBackReference
    private Member member;
}
