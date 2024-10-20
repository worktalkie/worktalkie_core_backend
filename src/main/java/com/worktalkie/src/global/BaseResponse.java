package com.worktalkie.src.global;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.worktalkie.src.global.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "data"})
public class BaseResponse<T> {
    private Boolean isSuccess;
    private String code;
    private T data;

    public BaseResponse(ErrorCode errorCode) {
        this.isSuccess = errorCode.isSuccess();
        this.code = errorCode.getCode();
    }

    public static BaseResponse createResponse(Object data, ErrorCode errorCode) {
        return new BaseResponse<>(errorCode);
    }
}
