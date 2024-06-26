package org.example.board.repository;

import org.example.board.entity.Board;
import org.example.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    @Transactional
    void deleteByProduct(Product existingProduct);
}
