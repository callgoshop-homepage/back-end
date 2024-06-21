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
import org.example.product.entity.Product;
import org.example.product.entity.ProductOption;
import org.example.product.repository.ProductRepository;
import org.example.product.service.ProductService;
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
    private final ProductRepository productRepository;

//    장바구니에 상품 추가하는 구문
    @Data
    public static class AddCartRequest {
        public Long productId;
        public int count;
        public Long optionId;
    }

    @AllArgsConstructor
    @Getter
    public static class AddCartResponse {
        private Long cartItemId;
        private Long productId;
        private String productName;
        private String optionName;
        private int count;
        private double productPrice;
        private double optionPrice;
        private double totalPrice;
    }

    @PostMapping(value = "/add", consumes = APPLICATION_JSON_VALUE)
    public RsData<AddCartResponse> addCart(HttpServletRequest request, @RequestBody AddCartRequest addCartRequest) {
        String token = jwtController.extractTokenFromHeader(request);
        String username = jwtProvider.getUsername(token);

        // Product와 ProductOption을 찾습니다.
        Product product = productRepository.findById(addCartRequest.getProductId())
                .orElseThrow(() -> new RuntimeException("상품을 찾을 수 없습니다."));

        ProductOption productOption = product.getProductOptions().stream()
                .filter(option -> option.getId().equals(addCartRequest.getOptionId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("옵션을 찾을 수 없습니다."));

        CartItem cartItem = cartService.addCart(
                addCartRequest.getProductId(),
                addCartRequest.getCount(),
                addCartRequest.getOptionId(),
                username
        );

        double totalPrice = cartItem.getCount() * (product.getPrice() + productOption.getOptionPrice());

        AddCartResponse response = new AddCartResponse(
                cartItem.getId(),
                product.getId(),
                product.getProductName(),
                productOption.getOptionName(),
                cartItem.getCount(),
                product.getPrice(),
                productOption.getOptionPrice(),
                totalPrice
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