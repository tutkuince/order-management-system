package com.incetutku.ordermanagementsystem.event.action;

import java.util.Arrays;

public enum Action {
    PAYMENT("P"), EMAIL("E"), COMPLETE_PAYMENT("C");

    private String code;

    Action(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static Action getActionByName(final String name) {
        return Arrays.stream(Action.values())
                .filter(action -> action.name().equalsIgnoreCase(name))
                .findFirst().orElse(null);
    }
}
