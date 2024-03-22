package br.com.yesv.ecommercems.adapters.in.controller.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {GlobalExceptionHandler.class})
@ExtendWith(SpringExtension.class)
class GlobalExceptionHandlerTest {

    @Autowired
    private GlobalExceptionHandler globalExceptionHandler;

    @Test
    void testHandleBadRequest() {
        var ex = new RuntimeException("test");
        var request = mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("https://example.org/example");

        var actualHandleBadRequestResult = globalExceptionHandler.handleBadRequest(ex,
                new ServletWebRequest(request));

        verify(request).getRequestURI();
        var body = actualHandleBadRequestResult.getBody();
        assertEquals("BAD_REQUEST", body.getMessage());
        assertEquals("test", body.getError());
        assertEquals("https://example.org/example", body.getPath());
        assertEquals(400, body.getStatus());
        assertEquals(400, actualHandleBadRequestResult.getStatusCodeValue());
        assertTrue(actualHandleBadRequestResult.hasBody());
        assertTrue(actualHandleBadRequestResult.getHeaders().isEmpty());
    }

    @Test
    void testHandleException() {
        var exception = new Exception("test");
        var request = mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("https://example.org/example");

        var actualHandleExceptionResult = globalExceptionHandler.handleException(exception,
                new ServletWebRequest(request));

        verify(request).getRequestURI();
        var body = actualHandleExceptionResult.getBody();
        assertEquals("INTERNAL_SERVER_ERROR", body.getMessage());
        assertEquals("MessageError: testDetail: ", body.getError());
        assertEquals("https://example.org/example", body.getPath());
        assertEquals(500, body.getStatus());
        assertEquals(500, actualHandleExceptionResult.getStatusCodeValue());
        assertTrue(actualHandleExceptionResult.hasBody());
        assertTrue(actualHandleExceptionResult.getHeaders().isEmpty());
    }

    @Test
    void testHandleConstraintViolationException() {

        var constraintViolation = mock(ConstraintViolation.class);
        when(constraintViolation.getPropertyPath()).thenReturn(mock(Path.class));
        when(constraintViolation.getMessage()).thenReturn("Not all who wander are lost");

        HashSet<ConstraintViolation<?>> constraintViolations = new HashSet<>();
        constraintViolations.add(constraintViolation);
        var constraintViolationException = new ConstraintViolationException(constraintViolations);

        var actualHandleConstraintViolationExceptionResult = globalExceptionHandler
                .handleConstraintViolationException(constraintViolationException,
                        new ServletWebRequest(new MockHttpServletRequest()));

        verify(constraintViolation, atLeast(1)).getMessage();
        verify(constraintViolation).getPropertyPath();
        var body = actualHandleConstraintViolationExceptionResult.getBody();
        assertEquals("", body.getPath());
        assertEquals("BAD_REQUEST", body.getMessage());
        assertEquals("Not all who wander are lost", body.getError());
        assertEquals(400, body.getStatus());
        assertEquals(400, actualHandleConstraintViolationExceptionResult.getStatusCodeValue());
        assertTrue(actualHandleConstraintViolationExceptionResult.hasBody());
        assertTrue(actualHandleConstraintViolationExceptionResult.getHeaders().isEmpty());
    }

    @Test
    void testHandleMethodArgumentTypeMismatchException() {

        Class<Object> requiredType = Object.class;
        MethodArgumentTypeMismatchException exception = new MethodArgumentTypeMismatchException("Value", requiredType,
                "0123456789ABCDEF", null, new Throwable());

        var actualHandleMethodArgumentTypeMismatchExceptionResult = globalExceptionHandler
                .handleMethodArgumentTypeMismatchException(exception, new ServletWebRequest(new MockHttpServletRequest()));

        var body = actualHandleMethodArgumentTypeMismatchExceptionResult.getBody();
        assertEquals("", body.getPath());
        assertEquals("'0123456789ABCDEF': The format of the field value is invalid.", body.getError());
        assertEquals("BAD_REQUEST", body.getMessage());
        assertEquals(400, body.getStatus());
        assertEquals(400, actualHandleMethodArgumentTypeMismatchExceptionResult.getStatusCodeValue());
        assertTrue(actualHandleMethodArgumentTypeMismatchExceptionResult.hasBody());
        assertTrue(actualHandleMethodArgumentTypeMismatchExceptionResult.getHeaders().isEmpty());
    }

}
