package org.example.productorder.dto;

import jakarta.validation.constraints.Min;
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

    //    승인 상태
    private int approveStatus;

    //    수량
    @Min(value = 1, message = "최소 1개 이상 담아주세요")
    private int count;

    // 총 금액
    private Long totalPrice;
}
