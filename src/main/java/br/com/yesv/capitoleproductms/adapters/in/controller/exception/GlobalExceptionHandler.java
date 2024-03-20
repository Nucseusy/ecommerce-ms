package br.com.yesv.capitoleproductms.adapters.in.controller.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class})
    private ResponseEntity<ApiErrorResponse> handleBadRequest(RuntimeException ex, WebRequest request) {
        return buildResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, ((ServletWebRequest) request).getRequest());

    }

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    private ResponseEntity<ApiErrorResponse> handleNotFound(RuntimeException ex, WebRequest request) {
        return buildResponse(ex.getMessage(), HttpStatus.NOT_FOUND, ((ServletWebRequest) request).getRequest());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleException(Exception exception, WebRequest request) {
        var message = "MessageError: ".concat(Optional.ofNullable(exception.getMessage())
                .orElse(""));
        var detail = "Detail: ".concat(Optional.ofNullable(exception.getCause())
                .map(Throwable::getMessage)
                .orElse(""));

        return buildResponse(message.concat(detail), HttpStatus.INTERNAL_SERVER_ERROR, ((ServletWebRequest) request).getRequest());

    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiErrorResponse> handleConstraintViolationException(ConstraintViolationException constraintViolationException, WebRequest request) {

        var errors = constraintViolationException.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());

        return buildResponse(String.join(", ", errors), HttpStatus.BAD_REQUEST, ((ServletWebRequest) request).getRequest());

    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception, WebRequest request) {

        var message = "'".concat(Optional.ofNullable(exception.getPropertyName()).orElse(""))
                .concat("': The format of the field value is invalid.");

        return buildResponse(message, HttpStatus.BAD_REQUEST, ((ServletWebRequest) request).getRequest());

    }

    private static ResponseEntity<ApiErrorResponse> buildResponse(String errors, HttpStatus httpStatus, HttpServletRequest servletRequest) {
        return ResponseEntity.status(httpStatus).body(ApiErrorResponse.builder()
                .path(servletRequest.getRequestURI())
                .timestamp(LocalDateTime.now())
                .error(errors)
                .status(httpStatus.value())
                .message(httpStatus.name())
                .build());
    }

}
