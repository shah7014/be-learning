package com.example.learning.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class AppExceptionHandler {

    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<ErrorMessage> departNotFoundExceptionHandler(DepartmentNotFoundException departmentNotFoundException) {
        log.info("DepartmentNotFoundException occurred");
        ErrorMessage errorMessage = ErrorMessage.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(departmentNotFoundException.getMessage())
                .build();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorMessage);
    }
}
