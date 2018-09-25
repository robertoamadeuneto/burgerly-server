package com.burgerly.application.exception.handler;

import com.burgerly.application.exception.CartAlreadyFinishedException;
import com.burgerly.application.exception.ExceptionDetails;
import com.burgerly.application.exception.ResourceNotFoundException;
import com.burgerly.application.exception.details.ValidationExceptionDetails;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Class responsible to handle REST exceptions.
 *
 * @author Roberto Amadeu Neto
 * @since 08/06/2018
 * @version 1.0
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rnfException) {
        ExceptionDetails rfnDetails = ExceptionDetails.Builder
                .newBuilder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Resource not found.")
                .detail(rnfException.getMessage())
                .developerMessage(rnfException.getClass().getName())
                .build();

        return new ResponseEntity<>(rfnDetails, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException manvException,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        List<FieldError> fieldErrors = manvException.getBindingResult().getFieldErrors();
        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(","));
        String fieldMessages = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));

        ValidationExceptionDetails veDetails = ValidationExceptionDetails.Builder
                .newBuilder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Field validation error.")
                .detail("Field validation error.")
                .developerMessage(manvException.getClass().getName())
                .field(fields)
                .fieldMessage(fieldMessages)
                .build();

        return new ResponseEntity<>(veDetails, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException hmnrException,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        ExceptionDetails errorDetails = ExceptionDetails.Builder
                .newBuilder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("HTTP Message not readable.")
                .detail(hmnrException.getMessage())
                .developerMessage(hmnrException.getClass().getName())
                .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
            Object body,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        ExceptionDetails errorDetails = ExceptionDetails.Builder
                .newBuilder()
                .timestamp(new Date().getTime())
                .status(status.value())
                .title("Internal exception.")
                .detail(ex.getMessage())
                .developerMessage(ex.getClass().getName())
                .build();
        return new ResponseEntity<>(errorDetails, headers, status);
    }

    @ExceptionHandler(CartAlreadyFinishedException.class)
    public ResponseEntity<?> handleCartAlreadyFinishedException(CartAlreadyFinishedException cafException) {
        ExceptionDetails rfnDetails = ExceptionDetails.Builder
                .newBuilder()
                .timestamp(new Date().getTime())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Cart already finished.")
                .detail(cafException.getMessage())
                .developerMessage(cafException.getClass().getName())
                .build();

        return new ResponseEntity<>(rfnDetails, HttpStatus.NOT_FOUND);
    }
}
