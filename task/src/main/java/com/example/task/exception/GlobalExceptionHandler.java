package com.example.task.exception;

import com.example.task.exception.exceptions.UserRepositoriesNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice()
public class GlobalExceptionHandler {
    @ExceptionHandler({UserRepositoriesNotFound.class})
    public ResponseEntity<Map<String, String>> handleUserNotFoundException(Exception e){
        Map<String, String> body = new HashMap<>();
        body.put("message", e.getMessage());
        body.put("status", "404");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}
