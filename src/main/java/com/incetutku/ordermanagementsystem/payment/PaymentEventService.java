package com.incetutku.ordermanagementsystem.payment;

import com.incetutku.ordermanagementsystem.order.dto.CompleteOrder;
import com.incetutku.ordermanagementsystem.order.dto.OrderPaymentDto;
import com.incetutku.ordermanagementsystem.payment.type.PaymentStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class PaymentEventService {

    private final PaymentRepository paymentRepository;

    public PaymentEventService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @ApplicationModuleListener
    @Async
    void on(final OrderPaymentDto orderPaymentDto) {
        log.info("Listener received in listener {}", orderPaymentDto);

        Payment payment = new Payment();
        payment.setOrderId(orderPaymentDto.orderId());
        payment.setAmount(orderPaymentDto.amount());
        paymentRepository.save(payment);
    }

    @ApplicationModuleListener
    void on(final CompleteOrder completeOrder) {
        log.info("Complete order received in listener: {}", completeOrder);

        Optional<Payment> optionalPayment = paymentRepository.findPaymentsByOrderId(completeOrder.orderIdentifier());

        if (optionalPayment.isPresent()) {
            Payment payment = optionalPayment.get();
            payment.setPaymentStatus(PaymentStatus.COMPLETED);
            paymentRepository.save(payment);
        }
    }
}
