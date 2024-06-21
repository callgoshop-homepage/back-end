package org.example.productorder.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.global.baseentity.BaseEntity;
import org.example.product.entity.Product;

import java.time.LocalDateTime;
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
    private String productOrderStatus;

//    수취인명
    private String reciever;

//    승인 처리일
    private LocalDateTime approvalDate;

//    배송 방법
    private String deliveryMethod;

//    택배사
    private String parcelCompany;

//    송장 번호
    private Long invoiceNumber;

//    담당자
    private String manager;

    @OneToMany(mappedBy = "productOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<Product> products;
}
