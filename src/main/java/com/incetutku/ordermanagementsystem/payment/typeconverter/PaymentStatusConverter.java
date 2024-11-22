package com.incetutku.ordermanagementsystem.payment.typeconverter;

import com.incetutku.ordermanagementsystem.exception.ModulithException;
import com.incetutku.ordermanagementsystem.payment.type.PaymentStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.Objects;

@Converter(autoApply = true)
public class PaymentStatusConverter implements AttributeConverter<PaymentStatus, String> {

    @Override
    public String convertToDatabaseColumn(PaymentStatus paymentStatus) {
        if (Objects.isNull(paymentStatus)) {
            throw new ModulithException("Payment Status can not be null");
        }
        return paymentStatus.getCode();
    }

    @Override
    public PaymentStatus convertToEntityAttribute(String code) {
        if (Objects.isNull(code)) {
            throw new ModulithException("Code can not be null");
        }
        return Arrays.stream(PaymentStatus.values())
                .filter(s -> s.getCode().equalsIgnoreCase(code))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
