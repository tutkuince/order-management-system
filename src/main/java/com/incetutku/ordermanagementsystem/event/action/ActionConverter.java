package com.incetutku.ordermanagementsystem.event.action;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.Objects;

@Converter(autoApply = true)
public class ActionConverter implements AttributeConverter<Action, String> {

    @Override
    public String convertToDatabaseColumn(Action action) {
        if (Objects.isNull(action)) {
            throw new RuntimeException("Action is null");
        }
        return action.getCode();
    }

    @Override
    public Action convertToEntityAttribute(String code) {
        if (Objects.isNull(code)) {
            throw new RuntimeException("Code is null");
        }
        return Arrays.stream(Action.values())
                .filter(action -> action.getCode().equalsIgnoreCase(code))
                .findFirst().orElseThrow(() -> new RuntimeException("Action not found"));
    }
}
