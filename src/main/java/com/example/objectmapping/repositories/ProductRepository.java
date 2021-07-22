package com.example.objectmapping.repositories;

import com.example.objectmapping.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
