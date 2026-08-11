package com.cafeapp.backend.repository;

import com.cafeapp.backend.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findByTableNumber(int tableNumber);
    List<OrderEntity> findByTableNumberAndPaid(int tableNumber, boolean paid);
    List<OrderEntity> findByStatusNot(String status);
    List<OrderEntity> findByPaidTrue();
}
