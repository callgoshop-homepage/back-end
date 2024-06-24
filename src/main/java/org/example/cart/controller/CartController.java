package org.example.cart.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.board.entity.Board;
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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/cart")
public class CartController {

    private final CartService cartService;
    private final JwtController jwtController;
    private final JwtProvider jwtProvider;
    private final ProductRepository productRepository;

    //    장바구니에 상품 추가하는 구문

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

    @PostMapping(value = "/add")
    public RsData<List<AddCartResponse>> addCart(HttpServletRequest request, @RequestBody AddCartRequest addCartRequest) {
        String token = jwtController.extractTokenFromHeader(request);
        String username = jwtProvider.getUsername(token);

        List<CartItem> cartItems = cartService.addCart(
                addCartRequest.getProductId(),
                addCartRequest.getCount(),
                addCartRequest.getOptions(),
                username
        );

        List<AddCartResponse> responses = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            Product product = cartItem.getProduct();
            ProductOption productOption = cartItem.getProductOption();
            double totalPrice = cartItem.getCount() * (product.getPrice() + (productOption != null ? productOption.getOptionPrice() : 0));

            AddCartResponse response = new AddCartResponse(
                    cartItem.getId(),
                    product.getId(),
                    product.getProductName(),
                    productOption != null ? productOption.getOptionName() : "옵션 하늘나라 감",
                    cartItem.getCount(),
                    product.getPrice(),
                    productOption != null ? productOption.getOptionPrice() : 0,
                    totalPrice
            );
            responses.add(response);
        }
        return RsData.of("S-10", "장바구니 추가 성공", responses);
    }

//    로그인 유저의 장바구니 리스트 조회하는 구문

    @AllArgsConstructor
    @Getter
    public static class CartItemResponse {
        private Long cartItemId;
        private Long productId;
        private String productName;
        private List<Board> board;
        private ProductOption productOption;
        private int count;
        private double productPrice;
        private double totalPrice;
    }

    @GetMapping(value = "/cartList")
    public RsData<List<CartItemResponse>> cartList(HttpServletRequest request) {
        String token = jwtController.extractTokenFromHeader(request);
        String username = jwtProvider.getUsername(token);

        List<CartItem> cartItemList = cartService.getCartList(username);
        List<CartItemResponse> response = cartItemList.stream()
                .map(cartItem -> new CartItemResponse(
                        cartItem.getId(),
                        cartItem.getProduct().getId(),
                        cartItem.getProduct().getProductName(),
                        cartItem.getProduct().getBoards(),
                        cartItem.getProductOption(),
                        cartItem.getCount(),
                        cartItem.getProduct().getPrice(),
                        cartItem.getCount() * cartItem.getProduct().getPrice()
                ))
                .collect(Collectors.toList());

        return RsData.of("S-11", "리스트 조회 성공", response);
    }

//  장바구니 삭제하는 구문
    @Data
    public static class DeleteCartRequest {
        private List<Long> Ids;
    }

    @DeleteMapping(value = "/delete", consumes = APPLICATION_JSON_VALUE)
    public RsData<?> deleteCart (@RequestBody DeleteCartRequest deleteCartRequest) {
        cartService.deleteCart(deleteCartRequest.getIds());

        return RsData.of("S-12", "장바구니 삭제 성공", null);
    }
}