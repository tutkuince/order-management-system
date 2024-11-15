package com.incetutku.ordermanagementsystem.order.type;

public enum Status {
    OPEN("O"), COMPLETED("C");

    private final String code;

    Status(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
