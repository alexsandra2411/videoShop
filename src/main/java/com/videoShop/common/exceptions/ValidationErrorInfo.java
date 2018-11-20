package com.videoShop.common.exceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.web.bind.MethodArgumentNotValidException;

/**
 * Error informations that contains object validation errors.
 * It will be converted to JSON Message
 */
public class ValidationErrorInfo {
    
    public final String url;
    public final List<FieldError> fieldErrors;
    public final List<GlobalError> globalErrors;

    public ValidationErrorInfo(String url, MethodArgumentNotValidException ex) {
        this.url = url;
        this.fieldErrors = ex.getBindingResult().getFieldErrors().stream().collect(() -> new ArrayList<FieldError>(),
                (c, e) -> c.add(new FieldError(e.getField(),e.getDefaultMessage())),
                (c1, c2) -> c1.addAll(c2));
        this.globalErrors = ex.getBindingResult().getGlobalErrors().stream().collect(() -> new ArrayList<GlobalError>(),
                (c, e) -> c.add(new GlobalError(e.getDefaultMessage())),
                (c1, c2) -> c1.addAll(c2));
    }
    public ValidationErrorInfo(String url, Set<ConstraintViolation<?>> constraintViolations) {
        this.url = url;
        this.fieldErrors = constraintViolations.parallelStream().collect(() -> new ArrayList<FieldError>(),
                (c, e) -> c.add(new FieldError(e.getPropertyPath().toString(),e.getMessage())),
                (c1, c2) -> c1.addAll(c2));
        this.globalErrors = null;
    }
    static class FieldError{
        public final String field;
        public final String message;

        public FieldError(String field, String message) {
            this.field = field;
            this.message = message;
        }
        
    }
    
    static class GlobalError{
        public final String message;

        public GlobalError(String message) {
            this.message = message;
        }
        
    }
}