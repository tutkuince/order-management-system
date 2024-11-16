package com.incetutku.ordermanagementsystem.payment.type;

public enum PaymentStatus {
    INCOMPLETE("I"), COMPLETED("C"), FAILED("F");

    private final String code;

    PaymentStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
