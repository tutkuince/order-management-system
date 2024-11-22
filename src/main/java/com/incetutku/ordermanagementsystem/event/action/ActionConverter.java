package com.incetutku.ordermanagementsystem.event.action;

import com.incetutku.ordermanagementsystem.exception.ModulithException;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.Objects;

@Converter(autoApply = true)
public class ActionConverter implements AttributeConverter<Action, String> {

    @Override
    public String convertToDatabaseColumn(Action action) {
        if (Objects.isNull(action)) {
            throw new ModulithException("Action is null");
        }
        return action.getCode();
    }

    @Override
    public Action convertToEntityAttribute(String code) {
        if (Objects.isNull(code)) {
            throw new ModulithException("Code is null");
        }
        return Arrays.stream(Action.values())
                .filter(action -> action.getCode().equalsIgnoreCase(code))
                .findFirst().orElseThrow(() -> new ModulithException("Action not found"));
    }
}
