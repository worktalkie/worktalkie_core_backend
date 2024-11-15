package com.worktalkie.src.global.error;

import lombok.Getter;

@Getter
public enum ErrorCode {

	/**
	 * Common
	 */
	BAD_REQUEST(false, "C-001", "잘못된 요청입니다"),
	NOT_FOUND(false, "C-002", "찾을 수 없는 리소스입니다"),
	INTERNAL_SERVER_ERROR(false, "C-003", "서버와의 연결에 실패하였습니다.");

	private final boolean isSuccess;
	private final String code;
	private final String message;

	ErrorCode(boolean isSuccess, String code, String message) {
		this.isSuccess = isSuccess;
		this.code = code;
		this.message = message;
	}

	public static ErrorCode findByStatusCode(String code) {
		for (ErrorCode errorCode : ErrorCode.values()) {
			if (errorCode.getCode().equals(code)) {
				return errorCode;
			}
		}
		throw new BaseException(ErrorCode.NOT_FOUND);
	}

}
