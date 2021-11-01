package com.xuan.diary.common.exception;

import com.xuan.diary.common.response.Response;
import com.xuan.diary.common.response.ResponseCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {
    /**
     * handle Not Found Exception
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
    ResponseEntity<Response<Object>> handleNotFoundException(Exception ex) {
        Response<Object> response = new Response<>();
        response.setStatus(ResponseCode.R_404);
        response.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
