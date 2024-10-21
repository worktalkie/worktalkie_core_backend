package com.worktalkie.src.global.code;

public enum HeaderType {
    CONTENT_TYPE("Content-Type"),
    ACCEPT("Accept");

    private final String value;

    HeaderType(String value) {
        this.value = value;
    }
}
