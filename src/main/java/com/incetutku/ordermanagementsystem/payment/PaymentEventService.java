package com.incetutku.ordermanagementsystem.payment;

import com.incetutku.ordermanagementsystem.order.dto.OrderPaymentDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

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
        System.out.println("Listener received in listener " + orderPaymentDto);
    }
}
