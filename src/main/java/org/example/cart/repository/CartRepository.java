package org.example.cart.repository;

import org.example.cart.entity.Cart;
import org.example.cart.entity.CartItem;
import org.example.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByMemberUsername(String username);

    Cart findByMemberId(Long id);

    List<CartItem> findByMember(Member member);
}
