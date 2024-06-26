package org.example.product.repository;

import org.example.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByType(String type);
    Product findById(long id);

    List<Product> findTop4ByOrderByCreateDateDesc();
}
