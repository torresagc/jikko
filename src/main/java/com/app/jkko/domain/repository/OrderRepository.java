package com.app.jkko.domain.repository;

import com.app.jkko.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface  OrderRepository extends JpaRepository<Order, UUID> {
}
