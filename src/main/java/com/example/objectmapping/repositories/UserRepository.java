package com.example.objectmapping.repositories;

import com.example.objectmapping.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u JOIN FETCH u.soldProducts p WHERE u.soldProducts.size > 0 AND p.buyer IS NOT NULL")
    Set<User> findAllWithSoldProducts();
}
