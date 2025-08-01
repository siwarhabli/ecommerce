package com.codewebprojects.ecom.repository;

import com.codewebprojects.ecom.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository  extends JpaRepository <Product,Long> {
}
