package com.mini_project.employeemanagementsystem.common;

import com.mini_project.employeemanagementsystem.common.exceptions.DataNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationError(MethodArgumentNotValidException e){
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(
                (error) ->{
                    String field = ((org.springframework.validation.FieldError)error).getField();
                    String value = error.getDefaultMessage();
                    errors.put(field, value);
                }
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<String> handleDataNotFoundException(DataNotFoundException dataNotFoundException) throws InterruptedException {
        System.out.println("Oops! Data not found.");
        System.out.print("Stack trace:");
        Thread.sleep(1000);
        dataNotFoundException.printStackTrace();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dataNotFoundException.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) throws InterruptedException {
        System.err.println("Oops! An error occured.");
        System.err.print("Stack trace:");
        Thread.sleep(1000);
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}
