package com.club.membership.exception;

import com.club.membership.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RemotePlatformException.class)
    public ResponseEntity<ErrorResponse> handleRemotePlatformException(
            RemotePlatformException ex
    ) {
        ErrorResponse response = new ErrorResponse(
                "Bad Request",
                ex.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(
                response,
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException ex
    ) {
        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error ->
                        error.getField() + ": "
                                + error.getDefaultMessage()
                )
                .collect(Collectors.joining(", "));

        ErrorResponse response = new ErrorResponse(
                "Validation Failed",
                message,
                LocalDateTime.now()
        );

        return new ResponseEntity<>(
                response,
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnexpectedException(
            Exception ex
    ) {
        ErrorResponse response = new ErrorResponse(
                "Something Went Wrong",
                ex.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(
                response,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
