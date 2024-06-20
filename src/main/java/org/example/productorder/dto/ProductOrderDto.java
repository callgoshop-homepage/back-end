package org.example.productorder.dto;

import lombok.*;
import org.example.global.baseentity.BaseEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductOrderDto extends BaseEntity {

    private Long id;

    //    주문 번호
    private Long productOrderNumber;

    //    주문 상태
    private boolean productOrderStatus;

    //    구매자 명
    private String buyer;

    //    구매자 전화번호
    private String buyerPhoneNumber;

    //    수취인 명
    private String reciever;

    //    승인 처리일
    private LocalDateTime approvalDate;

    //    발송 처리일
    private LocalDateTime sendDate;

    //    배송 방법
    private String deliveryMethod;

    //    택배사
    private String parcelCompany;

    //    송장 번호
    private Long invoiceNumber;

    //    상품 명
    private String productName;

    //    옵션 정보
    private String optionInfo;

    //    수량
    private int productQuantity;

    //    상품 가격
    private Long productPrice;

    //    옵션 가격
    private Long productOptionPrice;

    //    담당자
    private String manager;
}