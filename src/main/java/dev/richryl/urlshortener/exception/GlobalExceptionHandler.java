package dev.richryl.urlshortener.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    String handleConstraintValidationError(ConstraintViolationException e) {
        String error = buildErrorMessageFromConstraintValidationErrors(e);
        System.err.println(error);
//        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        return error;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        String error = result.getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + " : " + fieldError.getDefaultMessage())
                .reduce("", (acc, fieldError) -> acc + fieldError + "\n");
        return ResponseEntity.badRequest().body(error);
    }

    private static String buildErrorMessageFromConstraintValidationErrors(ConstraintViolationException e) {
        StringBuilder errorMessage = new StringBuilder();
        e.getConstraintViolations().forEach(
                constraintViolation -> errorMessage
                        .append(constraintViolation.getPropertyPath().toString().split("\\.")[1])
                        .append(" : ")
                        .append(constraintViolation.getMessage())
                        .append(" : rejected value [")
                        .append(constraintViolation.getInvalidValue())
                        .append("]")
                        .append("\n"));
        return errorMessage.toString();
    }
}
