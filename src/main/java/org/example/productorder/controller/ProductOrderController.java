package org.example.productorder.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.global.jwt.JwtProvider;
import org.example.global.rs.RsData;
import org.example.product.entity.Product;
import org.example.product.entity.ProductOption;
import org.example.productorder.dto.ProductOrderDto;
import org.example.productorder.entity.ProductOrder;
import org.example.productorder.service.ProductOrderService;
import org.example.token.controller.JwtController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/order", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class ProductOrderController {

    private final ProductOrderService productOrderService;
    private final JwtController jwtController;
    private final JwtProvider jwtProvider;

    //    주문 신청하는 구문
    @AllArgsConstructor
    @Getter
    public static class ProductsOrderResponse {
        private final ProductOrder productOrder;
    }

    @PostMapping(value = "/add")
    public RsData<ProductsOrderResponse> addProductOrder(@RequestBody ProductOrderDto productOrderDto, HttpServletRequest request) {
        String token = jwtController.extractTokenFromHeader(request);
        String username = jwtProvider.getUsername(token);

        ProductOrder productOrder = productOrderService.addProductOrder(productOrderDto, username);

        return RsData.of("S-20", "상품 주문 성공", new ProductsOrderResponse(productOrder));
    }

    //    주문 리스트 불러오는 구문
    @AllArgsConstructor
    @Getter
    public static class ProductsOrderListResponse {
        private final List<ProductOrder> productOrderList;
    }

    @GetMapping(value = "/list")
    public RsData<ProductsOrderListResponse> productOrderList() {
        List<ProductOrder> productOrderList = productOrderService.productOrderList();

        return RsData.of("S-21", "주문 리스트 조회 성공", new ProductsOrderListResponse(productOrderList));
    }

    //    주문 리스트 수정하는 구문
    @AllArgsConstructor
    @Getter
    public static class ProductsOrderModifyResponse {
        private final ProductOrder productOrder;
    }

    @PutMapping(value = "/modify")
    public RsData<ProductsOrderModifyResponse> productOrderModify(@RequestBody ProductOrderDto productOrderDto) {
        ProductOrder productOrder = productOrderService.modifyProductOrder(productOrderDto);

        return RsData.of("S-22", "주문 리스트 수정 완료", new ProductsOrderModifyResponse(productOrder));
    }
}
