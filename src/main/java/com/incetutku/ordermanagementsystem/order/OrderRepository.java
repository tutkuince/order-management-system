package com.incetutku.ordermanagementsystem.order;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

interface OrderRepository extends CrudRepository<Order, Long> {
    Optional<Order> findOrderByOrderIdentifier(String orderIdentifier);
}
