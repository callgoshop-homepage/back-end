package org.example.cart.repository;

import org.example.cart.entity.Cart;
import org.example.cart.entity.CartItem;
import org.example.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByCartIdAndProductId(Long cartId, Long productId);

    CartItem findByCartIdAndProductIdAndProductOptionId(Long id, Long id1, Long optionId);

    List<CartItem> findByCart(Cart cart);
}
