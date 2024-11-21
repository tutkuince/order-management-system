package com.incetutku.ordermanagementsystem.order;

import com.incetutku.ordermanagementsystem.order.dto.EmailDto;
import com.incetutku.ordermanagementsystem.order.dto.OrderPaymentDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class OrderEventService {

    private final ApplicationEventPublisher eventPublisher;

    public OrderEventService(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public void completeOrder(final OrderPaymentDto orderPaymentDto, EmailDto emailDto) {
        log.info("Completing order payment with details{}", orderPaymentDto);
        eventPublisher.publishEvent(orderPaymentDto);

        log.info("Sending email for order {}", emailDto);
        eventPublisher.publishEvent(emailDto);
    }
}
