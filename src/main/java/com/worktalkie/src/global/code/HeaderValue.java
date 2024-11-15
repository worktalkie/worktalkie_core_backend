package com.worktalkie.src.global.code;

public enum HeaderValue {
	APPLICATION_JSON("application/json");

	private final String value;

	HeaderValue(String value) {
		this.value = value;
	}
}
