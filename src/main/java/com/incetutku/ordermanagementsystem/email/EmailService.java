package com.incetutku.ordermanagementsystem.email;

import com.incetutku.ordermanagementsystem.order.dto.EmailDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
public class EmailService {

    @ApplicationModuleListener
    void sendEmail(final EmailDto emailDto) {
        log.info("Email properties received!");

        if (emailDto.orderCompleted()) {
            String body = String.format("Dear %s, \n An Order was initialized with Id: %s and total sum of: N %s",
                    emailDto.customerName(), emailDto.orderIdentifier(), emailDto.totalAmount() / 100);
            log.info("initial Email order details: {}", body);
        } else {
            String body = String.format("Dear %s, \n Your Order with Id: %s and total sum of: N %s was completed successfully",
                    emailDto.customerName(), emailDto.orderIdentifier(), emailDto.totalAmount() / 100);
            log.info("Email completion details: {}", body);
        }

        log.info("Email sent to {}", emailDto.email());
    }
}
