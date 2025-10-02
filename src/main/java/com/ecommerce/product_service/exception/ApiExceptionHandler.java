package com.ecommerce.product_service.exception;

import com.ecommerce.product_service.exception.custom.NotFoundException;
import com.ecommerce.product_service.exception.custom.UnauthorizedException;
import com.ecommerce.product_service.model.response.ErrorResponse;
import com.ecommerce.product_service.model.response.ResponseMessage;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler {
    private static final String SUCCESS = "Error";

    @ExceptionHandler(value = DuplicateKeyException.class)
    public ResponseEntity<ErrorResponse> handleAlreadyExistsException(DuplicateKeyException e) {
        HttpStatus status = HttpStatus.CONFLICT;
        ErrorResponse response = new ErrorResponse(SUCCESS, e.getMessage(), status.value(), null);
        return new ResponseEntity<>(response, new HttpHeaders(), status);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponse response = new ErrorResponse(SUCCESS, e.getMessage(), status.value(), null);
        return new ResponseEntity<>(response, new HttpHeaders(), status);
    }

    @ExceptionHandler(value = EmptyResultDataAccessException.class)
    public ResponseEntity<ErrorResponse> handleEmptyResultDataAccessException(EmptyResultDataAccessException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponse response = new ErrorResponse(SUCCESS, e.getMessage(), status.value(), null);
        return new ResponseEntity<>(response, new HttpHeaders(), status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());

        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ErrorResponse response = new ErrorResponse(SUCCESS, ResponseMessage.DATA_INVALID, status.value(), errors);
        return new ResponseEntity<>(response, new HttpHeaders(), status);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleNotReadableExceptionErrors(HttpMessageNotReadableException ex) {
        List<String> errors = new ArrayList<>();
        errors.add(ResponseMessage.DATA_INVALID);

        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ErrorResponse response = new ErrorResponse(SUCCESS, ResponseMessage.DATA_INVALID, status.value(), errors);
        return new ResponseEntity<>(response, new HttpHeaders(), status);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchExceptionErrors(NoSuchElementException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponse response = new  ErrorResponse(SUCCESS, ResponseMessage.DATA_NOT_FOUND, status.value(), null);
        return new ResponseEntity<>(response, new HttpHeaders(), status);
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolation(DataIntegrityViolationException e) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponse response = new ErrorResponse(SUCCESS, ResponseMessage.DATA_INVALID, status.value(), null);
        return new ResponseEntity<>(response, new HttpHeaders(), status);
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedException(UnauthorizedException e) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        ErrorResponse response = new ErrorResponse(SUCCESS, ResponseMessage.UNAUTHORIZED, status.value(), null);
        return new ResponseEntity<>(response, new HttpHeaders(), status);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponse response = new ErrorResponse(SUCCESS, e.getMessage(), status.value(), null);
        return new ResponseEntity<>(response, new HttpHeaders(), status);
    }

}
