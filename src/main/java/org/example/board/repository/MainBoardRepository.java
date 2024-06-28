package org.example.board.repository;

import org.example.board.entity.MainBoard;
import org.example.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MainBoardRepository extends JpaRepository<MainBoard, Long> {
}
