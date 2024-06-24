package org.example.productorder.repository;

import org.example.productorder.entity.ProductOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOrderItemRepository extends JpaRepository<ProductOrderItem, Long> {
}
