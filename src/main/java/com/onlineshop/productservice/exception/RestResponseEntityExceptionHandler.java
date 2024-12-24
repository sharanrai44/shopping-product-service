package com.onlineshop.productservice.exception;

import com.onlineshop.productservice.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.onlineshop.productservice.controller.ProductsController.ERROR;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductException.class)
    public ResponseEntity<ApiResponse<Void>> handleResourceNotFoundException(ProductException ex) {
        ApiResponse<Void> response = new ApiResponse<>(
                ERROR,
                null,
                new ApiResponse.ErrorDetails(HttpStatus.BAD_REQUEST.name(), ex.getMessage())
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(Exception ex) {
        ApiResponse<Void> response = new ApiResponse<>(
                ERROR,
                null,
                new ApiResponse.ErrorDetails("INTERNAL_SERVER_ERROR", ex.getMessage())
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
