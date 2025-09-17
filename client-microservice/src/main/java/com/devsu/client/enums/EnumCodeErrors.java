package com.devsu.client.enums;

public enum EnumCodeErrors {
    SUCCESS(0),
    BUSINESS_ERROR(1),
    TECHNICAL_ERROR(-1);

    private final int code;

    EnumCodeErrors(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
