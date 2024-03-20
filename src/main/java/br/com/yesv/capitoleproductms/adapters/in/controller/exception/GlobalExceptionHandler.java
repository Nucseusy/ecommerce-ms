package br.com.yesv.capitoleproductms.adapters.in.controller.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

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
