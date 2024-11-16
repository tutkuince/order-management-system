package com.incetutku.ordermanagementsystem.payment;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

interface PaymentRepository extends CrudRepository<Payment, Long> {
    Optional<Payment> findPaymentsByOrderId(String orderId);
}
