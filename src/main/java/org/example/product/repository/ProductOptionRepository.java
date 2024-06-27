package org.example.product.repository;

import org.example.product.entity.Product;
import org.example.product.entity.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductOptionRepository extends JpaRepository<ProductOption, Long> {

}
