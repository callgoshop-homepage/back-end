package org.example.board.repository;

import org.example.board.entity.DetailBoard;
import org.example.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DetailBoardRepository extends JpaRepository<DetailBoard, Long> {
    @Transactional
    void deleteByProduct(Product existingProduct);
}
