package com.worktalkie.src.global;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "data"})
public class BaseResponse<T> {
    private Boolean isSuccess;
    private T data;

    public BaseResponse(T data) {
        this.isSuccess = true;
        this.data = data;
    }
}
