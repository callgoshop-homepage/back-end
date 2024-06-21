package org.example.cart.service;

import lombok.RequiredArgsConstructor;
import org.example.cart.controller.AddCartRequest;
import org.example.cart.entity.Cart;
import org.example.cart.entity.CartItem;
import org.example.cart.repository.CartItemRepository;
import org.example.cart.repository.CartRepository;
import org.example.member.entity.Member;
import org.example.member.repository.MemberRepository;
import org.example.product.entity.Product;
import org.example.product.entity.ProductOption;
import org.example.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;


    //    장바구니 추가하는 구문
    public List<CartItem> addCart(Long productId, List<AddCartRequest.OptionCount> options, String username) {

        Product product = productRepository.findById(productId).orElse(null);
        Member member = memberRepository.findByUsername(username).orElse(null);
        Cart cart = cartRepository.findByMemberId(member.getId());
        if (cart == null) {
            cart = Cart.builder()
                    .member(member)
                    .build();
            cartRepository.save(cart);
        }

        List<CartItem> cartItems = new ArrayList<>();
        for (AddCartRequest.OptionCount optionCount : options) {
            ProductOption productOption = product.getProductOptions().stream()
                    .filter(option -> option.getId().equals(optionCount.getOptionId()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("옵션을 찾을 수 없습니다."));

            // CartItem을 생성하거나 기존 항목을 업데이트합니다.
            CartItem cartItem = cartItemRepository.findByCartIdAndProductIdAndProductOptionId(cart.getId(), product.getId(), productOption.getId());

            productOption.setCartItems(cartItems);

            if (cartItem != null) {
                cartItem.addCount(optionCount.getCount());
                cartItemRepository.save(cartItem);
            } else {
                cartItem = CartItem.builder()
                        .cart(cart)
                        .product(product)
                        .productOption(productOption)
                        .count(optionCount.getCount())
                        .build();
                cartItemRepository.save(cartItem);
            }
            cartItems.add(cartItem);
        }

        cart.setCartItems(cartItems);
        product.setCartItems(cartItems);

        return cartItems;
    }

    //    장바구니 조회하는 구문
    public List<CartItem> getCartList(String username) {
        Member member = memberRepository.findByUsername(username).orElse(null);

        return cartRepository.findByMember(member);
    }
}
