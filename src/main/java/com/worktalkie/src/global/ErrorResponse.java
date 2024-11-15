package com.worktalkie.src.global;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.worktalkie.src.global.error.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code"})
public class ErrorResponse {
	private Boolean isSuccess;
	private String code;
	private String message;

	public ErrorResponse(ErrorCode errorCode) {
		this.isSuccess = errorCode.isSuccess();
		this.code = errorCode.getCode();
		this.message = errorCode.getMessage();
	}
}
