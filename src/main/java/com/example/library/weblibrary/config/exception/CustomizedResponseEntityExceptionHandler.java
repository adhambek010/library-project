package com.example.library.weblibrary.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler {

    /**
     * Global exception handler for handling all types of exceptions.
     * This method catches any unhandled exceptions and returns an error response with status code 500 (Internal Server Error).
     *
     * @param ex      The exception that occurred.
     * @param request The current web request.
     * @return ResponseEntity containing error details and status code 500.
     * @throws Exception if an error occurs while handling the exception.
     */
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) throws Exception {
//        var errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
//        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    /**
     * Exception handler for handling StudentNotFoundException.
     * This method catches StudentNotFoundException and returns an error response with status code 404 (Not Found).
     *
     * @param ex      The exception that occurred.
     * @param request The current web request.
     * @return ResponseEntity containing error details and status code 404.
     * @throws Exception if an error occurs while handling the exception.
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleUserNotFoundException(Exception ex, WebRequest request) throws Exception {
        var errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    /**
     * Overrides the default handler for HandlerMethodValidationException.
     * This method catches HandlerMethodValidationException and returns an error response with status code 400 (Bad Request).
     *
     * @param ex      The exception that occurred.
     * @param headers The HTTP headers for the response.
     * @param status  The HTTP status for the response.
     * @param request The current web request.
     * @return ResponseEntity containing error details and status code 400.
     */
//    @Override
//    protected ResponseEntity<Object> handleHandlerMethodValidationException(
//            HandlerMethodValidationException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
//        var errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
//        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
//    }

}
