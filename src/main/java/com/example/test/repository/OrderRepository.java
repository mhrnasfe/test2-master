package com.example.test.repository;

import com.example.test.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
//    Order findById();

    Optional<Order> findById(Long id);
}
