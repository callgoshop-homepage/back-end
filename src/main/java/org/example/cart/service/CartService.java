package org.example.cart.service;

import lombok.RequiredArgsConstructor;
import org.example.cart.dto.CartItemDto;
import org.example.cart.entity.Cart;
import org.example.cart.entity.CartItem;
import org.example.cart.repository.CartItemRepository;
import org.example.cart.repository.CartRepository;
import org.example.member.entity.Member;
import org.example.member.repository.MemberRepository;
import org.example.member.service.MemberService;
import org.example.product.entity.Product;
import org.example.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;


//    장바구니 추가하는 구문
    public CartItem addCart(Long productId ,int count , String username) {

        Product product = productRepository.findById(productId).orElse(null);
        Member member = memberRepository.findByUsername(username).orElse(null);

        Cart cart = cartRepository.findByMemberId(member.getId());
        if (cart == null) {
            cart = Cart.builder()
                    .member(member)
                    .build();
            cartRepository.save(cart);
        }

        CartItem cartItem = cartItemRepository.findByCartIdAndProductId(cart.getId(), product.getId());

        if (cartItem != null) {
            cartItem.addCount(count);
            return cartItem;
        } else {
            CartItem cartItem1 = CartItem.builder()
                    .cart(cart)
                    .product(product)
                    .count(count)
                    .build();
            cartItemRepository.save(cartItem1);
            return cartItem1;
        }
    }

//    장바구니 조회하는 구문
    public List<CartItem> getCartList(String username) {
        Member member = memberRepository.findByUsername(username).orElse(null);

        return cartRepository.findByMember(member);
    }
}
