package org.example.cart.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.cart.dto.CartItemDto;
import org.example.cart.entity.CartItem;
import org.example.cart.service.CartService;
import org.example.global.jwt.JwtProvider;
import org.example.global.rs.RsData;
import org.example.member.entity.Member;
import org.example.token.controller.JwtController;
import org.hibernate.validator.internal.engine.messageinterpolation.parser.TokenCollector;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/cart", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class CartController {

    private final CartService cartService;
    private final JwtController jwtController;
    private final JwtProvider jwtProvider;

//    장바구니에 상품 추가하는 구문
    @Data
    public static class AddCartRequest {
        public Long productId;
        public int count;
    }

    @AllArgsConstructor
    @Getter
    public static class AddCartResponse {
        private Long cartItemId;
        private Long productId;
        private String productName;
        private int count;
        private double productPrice;
        private double totalPrice;
    }

    @PostMapping(value = "/add", consumes = APPLICATION_JSON_VALUE)
    public RsData<AddCartResponse> addCart(HttpServletRequest request, @RequestBody AddCartRequest addCartRequest) {
        String token = jwtController.extractTokenFromHeader(request);
        String username = jwtProvider.getUsername(token);

        CartItem cartItem = cartService.addCart(addCartRequest.getProductId(), addCartRequest.getCount(), username);

        AddCartResponse response = new AddCartResponse(
                cartItem.getId(),
                cartItem.getProduct().getId(),
                cartItem.getProduct().getProductName(),
                cartItem.getCount(),
                cartItem.getProduct().getPrice(),
                cartItem.getCount() * cartItem.getProduct().getPrice()
        );
        return RsData.of("S-10" ,"장바구니 추가 성공", response);
    }


//    로그인 유저의 장바구니 리스트 조회하는 구문

    @AllArgsConstructor
    @Getter
    public static class CartItemResponse {
        private Long cartItemId;
        private Long productId;
        private String productName;
        private int count;
        private double productPrice;
        private double totalPrice;
    }

    @GetMapping(value = "/cartList", consumes = APPLICATION_JSON_VALUE)
    public RsData<List<CartItemResponse>> cartList (HttpServletRequest request){
        String token = jwtController.extractTokenFromHeader(request);
        String username = jwtProvider.getUsername(token);

        List<CartItem> cartItemList = cartService.getCartList(username);
        List<CartItemResponse> response = cartItemList.stream()
                .map(cartItem -> new CartItemResponse(
                        cartItem.getId(),
                        cartItem.getProduct().getId(),
                        cartItem.getProduct().getProductName(),
                        cartItem.getCount(),
                        cartItem.getProduct().getPrice(),
                        cartItem.getCount() * cartItem.getProduct().getPrice()
                ))
                .collect(Collectors.toList());

        return RsData.of("S-11", "리스트 조회 성공", response);
    }
}