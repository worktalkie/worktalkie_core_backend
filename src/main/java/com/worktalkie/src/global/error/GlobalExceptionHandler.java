package com.worktalkie.src.global.error;

import com.worktalkie.src.global.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public BaseResponse<ErrorCode> BaseException(BaseException error) {
        ErrorCode errorCode;

        try {
            errorCode = Optional.ofNullable(error.getCode())
                    .orElse(ErrorCode.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            log.error("error: {}", e.getMessage());
            errorCode = ErrorCode.INTERNAL_SERVER_ERROR;  // 예외 발생 시 기본 에러 코드로 처리
        }
        return new BaseResponse<>(errorCode);
    }
}
