package org.example.productorder.service;

import lombok.RequiredArgsConstructor;
import org.example.member.entity.Member;
import org.example.member.repository.MemberRepository;
import org.example.product.entity.Product;
import org.example.product.repository.ProductRepository;
import org.example.productorder.dto.ProductOrderDto;
import org.example.productorder.entity.ProductOrder;
import org.example.productorder.repository.ProductOrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductOrderService {

    private final ProductOrderRepository productOrderRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

//    주문 목록을 추가하는 구문
    public ProductOrder addProductOrder(ProductOrderDto productOrderDto, String username) {
        Optional<Member> member = memberRepository.findByUsername(username);

        Optional<Product> product = productRepository.findById(productOrderDto.getId());

            Long totalPrice = productOrderDto.getTotalPrice();

            ProductOrder productOrder = ProductOrder.builder()
                    .receiver(productOrderDto.getReciever())
                    .orderDate(LocalDateTime.now())
                    .approvalDate(null)
                    .deliveryMethod(null)
                    .parcelCompany(null)
                    .invoiceNumber(null)
                    .approveStatus(0)
                    .count(productOrderDto.getCount())
                    .totalPrice(totalPrice)
                    .member(member.get())
                    .product(product.get())
                    .build();

            productOrder.setProduct(product.get());
            productOrder.setMember(member.get());

           productOrderRepository.save(productOrder);

           return productOrder;
    }

//    주문 리스트 불러오기
    public List<ProductOrder> productOrderList () {
        return productOrderRepository.findAll();
    }

//    주문 내역 수정하는 구문
    public ProductOrder modifyProductOrder(ProductOrderDto productOrderDto) {
        Optional<ProductOrder> productOrder = productOrderRepository.findById(productOrderDto.getId());

        ProductOrder productOrderEntity = productOrder.get();
        productOrderEntity.setApprovalDate(LocalDateTime.now());
        productOrderEntity.setDeliveryMethod(productOrderDto.getDeliveryMethod());
        productOrderEntity.setParcelCompany(productOrderDto.getParcelCompany());
        productOrderEntity.setInvoiceNumber(productOrderDto.getInvoiceNumber());
        productOrderEntity.setApproveStatus(productOrderDto.getApproveStatus());

        productOrderRepository.save(productOrderEntity);

        return productOrderEntity;
    }
}
