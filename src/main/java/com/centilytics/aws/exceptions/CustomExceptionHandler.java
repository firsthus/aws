package com.centilytics.aws.exceptions;

import com.centilytics.aws.pojos.responses.APIResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<APIResponse<Object>> resourceNotFoundException(ResourceNotFoundException exception){
        HttpStatus status = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(prepareErrorResponse(status, exception), status);
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<APIResponse<Object>> badRequestException(BadRequestException exception){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(prepareErrorResponse(status, exception), status);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<APIResponse<Object>> genericException(Exception exception){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        log.error("Exception occurred: ", exception);
        return new ResponseEntity<>(prepareErrorResponse(status, exception), status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse<Object>> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getFieldErrors();
        final List<ValidationError> validationErrors = new ArrayList<>();
        for (FieldError fieldError: fieldErrors) {
            ValidationError error = new ValidationError(fieldError.getField(), fieldError.getDefaultMessage());
            validationErrors.add(error);
        }
        return new ResponseEntity<>(APIResponse.builder().success(false).message("Validation error").statusCode(BAD_REQUEST.value()).data(validationErrors).build(), BAD_REQUEST);
    }

    private static APIResponse<Object> prepareErrorResponse(HttpStatus status, Exception ex) {
        APIResponse<Object> response = new APIResponse<>();
        try {
            response.setSuccess(false);
            response.setMessage(ex.getMessage());
            response.setStatusCode(status.value());
        } catch (Exception e) {
            log.error("Exception occurred: ", e);
        }
        return response;
    }
}