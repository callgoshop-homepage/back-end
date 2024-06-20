package org.example.board.repository;

import org.example.board.entity.DetailBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailBoardRepository extends JpaRepository<DetailBoard, Long> {
}
