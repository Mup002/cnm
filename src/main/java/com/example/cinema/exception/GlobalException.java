package com.example.cinema.exception;

import com.example.cinema.utils.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ExceptionResponse.class)
    public ResponseEntity<BaseResponse> handleExceptionResponse(ExceptionResponse e){
        BaseResponse response = BaseResponse.builder()
                .statusCode(e.getStatusCode())
                .message(e.getMessage()).build();
        return ResponseEntity.ok(response);
    }
}
