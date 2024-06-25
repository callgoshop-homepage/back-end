package org.example.productorder.service;

import lombok.RequiredArgsConstructor;
import org.example.member.entity.Member;
import org.example.member.repository.MemberRepository;
import org.example.product.entity.Product;
import org.example.product.entity.ProductOption;
import org.example.product.repository.ProductRepository;
import org.example.productorder.dto.ProductOrderDto;
import org.example.productorder.dto.ProductOrderItemDto;
import org.example.productorder.entity.ProductOrder;
import org.example.productorder.entity.ProductOrderItem;
import org.example.productorder.repository.ProductOrderItemRepository;
import org.example.productorder.repository.ProductOrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductOrderService {

    private final ProductOrderRepository productOrderRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final ProductOrderItemRepository productOrderItemRepository;

//    주문 목록을 추가하는 구문
    public ProductOrder addProductOrder(ProductOrderDto productOrderDto, String username) {
        Optional<Member> member = memberRepository.findByUsername(username);

        System.out.println(productOrderDto);

            ProductOrder productOrder = ProductOrder.builder()
                    .receiver(productOrderDto.getReciever())
                    .orderDate(LocalDateTime.now())
                    .approvalDate(null)
                    .deliveryMethod(null)
                    .parcelCompany(null)
                    .invoiceNumber(null)
                    .approveStatus(0)
                    .address(productOrderDto.getAddress())
                    .totalPrice(0L)
                    .member(member.get())
                    .build();

        productOrderRepository.save(productOrder);

        Long totalPrice = 0L;
            for (ProductOrderItemDto itemDto : productOrderDto.getOrderItems()) {
                Optional<Product> product1 = productRepository.findById(itemDto.getProductId());
                Product product = product1.get();

                if (itemDto.getCount() > 0) {
                    ProductOrderItem productOrderItem = ProductOrderItem.builder()
                            .product(product)
                            .count(itemDto.getCount())
                            .productOrder(productOrder)
                            .build();

                    productOrder.addProductOrderItem(productOrderItem);
                    totalPrice += product.getPrice() * itemDto.getCount();
                }

                if (itemDto.getOptions() != null && !itemDto.getOptions().isEmpty()) {
                    for (ProductOrderItemDto.OptionCount optionCount : itemDto.getOptions()) {
                        ProductOption productOption = product.getProductOptions().stream()
                                .filter(option -> option.getId().equals(optionCount.getOptionId()))
                                .findFirst()
                                .orElseThrow(() -> new RuntimeException("옵션을 찾을 수 없다잖니"));

                        ProductOrderItem productOrderItem = ProductOrderItem.builder()
                                .product(product)
                                .productOption(productOption)
                                .count(optionCount.getCount())
                                .productOrder(productOrder)
                                .build();

                        productOrder.addProductOrderItem(productOrderItem);
                        totalPrice += product.getPrice() * optionCount.getCount();
                    }
                }
            }

        productOrder.setTotalPrice(totalPrice);
        productOrder = productOrderRepository.save(productOrder);

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
