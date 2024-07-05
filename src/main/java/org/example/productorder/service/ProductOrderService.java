package org.example.productorder.service;

import lombok.RequiredArgsConstructor;
import org.example.member.entity.Member;
import org.example.member.repository.MemberRepository;
import org.example.product.entity.Product;
import org.example.product.entity.ProductOption;
import org.example.product.repository.ProductOptionRepository;
import org.example.product.repository.ProductRepository;
import org.example.productorder.dto.ProductOrderDto;
import org.example.productorder.dto.ProductOrderItemDto;
import org.example.productorder.entity.ProductOrder;
import org.example.productorder.entity.ProductOrderItem;
import org.example.productorder.repository.ProductOrderItemRepository;
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
    private final ProductOrderItemRepository productOrderItemRepository;
    private final ProductOptionRepository productOptionRepository;

    //    주문 목록을 추가하는 구문
    public ProductOrder addProductOrder(ProductOrderDto productOrderDto, String username) {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        ProductOrder productOrder = ProductOrder.builder()
                .orderStatus("승인대기")
                .reciever(productOrderDto.getReciever())
                .orderDate(LocalDateTime.now())
                .approvalDate(null)
                .deliveryMethod("미등록")
                .parcelCompany("미등록")
                .invoiceNumber(0L)
                .approveStatus("미승인")
                .address(productOrderDto.getAddress())
                .totalPrice(0L)
                .memeberName(member.getName())
                .member(member)
                .buyer(member.getUsername())
                .buyerPhoneNumber(member.getPhoneNumber())
                .build();

        productOrderRepository.save(productOrder);

        Long totalPrice = 0L;
        for (ProductOrderItemDto itemDto : productOrderDto.getOrderItems()) {
            Product product = productRepository.findById(itemDto.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

//            productOrder.setProductName(product.getProductName());

            if (itemDto.getCount() > 0) {
                ProductOrderItem productOrderItem = ProductOrderItem.builder()
//                        .product(product)
                        .productName(product.getProductName())
                        .count(itemDto.getCount())
                        .productOrder(productOrder)
                        .optionName("옵션 없음")
                        .optionPrice(0L)
                        .build();

                productOrder.addProductOrderItem(productOrderItem);
                productOrderItemRepository.save(productOrderItem);
                totalPrice += product.getPrice() * itemDto.getCount();
            }

            if (itemDto.getOptions() != null && !itemDto.getOptions().isEmpty()) {
                for (ProductOrderItemDto.OptionCount optionCount : itemDto.getOptions()) {
//                    System.out.println("옵션 옵션 옵션 옵션 옵션 옵션 옵션 옵션 옵션 옵션" + optionCount);

                    ProductOption productOption = productOptionRepository.findById(optionCount.getOptionId())
                            .orElseThrow(() -> new RuntimeException("Option not found"));
//                    내일 할거 여기 runtimeexcepption 오류 터짐

                    ProductOrderItem productOrderItem = ProductOrderItem.builder()
//                            .product(product)
//                            .productOption(productOption)
                            .count(optionCount.getCount())
                            .productName(product.getProductName())
                            .optionName(productOption.getOptionName())
                            .optionPrice(productOption.getOptionPrice())
                            .productOrder(productOrder)
                            .build();

                    productOrder.addProductOrderItem(productOrderItem);
                    productOrderItemRepository.save(productOrderItem);
                    totalPrice += (product.getPrice() + productOption.getOptionPrice()) * optionCount.getCount();
                }
            }
        }

        productOrder.setTotalPrice(totalPrice);
        return productOrderRepository.save(productOrder);
    }

//    주문 리스트 불러오기
    public List<ProductOrder> productOrderList () {
        return productOrderRepository.findAllByOrderByIdDesc();
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
        if (productOrderDto.getApproveStatus().equals("재고부족")){
            productOrderEntity.setOrderStatus("승인거부");
        } else if (productOrderDto.getApproveStatus().equals("승인")) {
            productOrderEntity.setOrderStatus("승인완료");
        } else {
            productOrderEntity.setOrderStatus("승인대기");
        }
        productOrderRepository.save(productOrderEntity);

        return productOrderEntity;
    }
//    id로 상품 주문 상세 정보 가져오는 구문
    public ProductOrder getProductOrderById(Long id) {
        Optional<ProductOrder> productOrder = productOrderRepository.findById(id);
        return productOrder.get();
    }
}
