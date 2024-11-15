package com.incetutku.ordermanagementsystem.order.typeconverter;

import com.incetutku.ordermanagementsystem.order.type.Status;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, String> {

    @Override
    public String convertToDatabaseColumn(Status status) {
        return Arrays.stream(Status.values())
                .filter(s -> s.equals(status))
                .map(Status::getCode)
                .findFirst().orElseThrow(() -> new IllegalArgumentException("No status found"));
    }

    @Override
    public Status convertToEntityAttribute(String code) {
        return Arrays.stream(Status.values())
                .filter(s -> s.getCode().equalsIgnoreCase(code))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("No status found"));
    }
}
