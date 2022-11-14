package com.practicemustach.bbs2.repository;

import com.practicemustach.bbs2.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
