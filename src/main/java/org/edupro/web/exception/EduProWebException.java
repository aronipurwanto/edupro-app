package org.edupro.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.util.List;

public class EduProWebException extends RuntimeException {
    private final HttpStatus status;
    private final String message;
    private final List<FieldError> errors;

    public EduProWebException(String message, List<FieldError> errors) {
        super(message);
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        this.message = message;
        this.errors = errors;
    }

    public EduProWebException(String message, HttpStatus status) {
        super(message);
        this.status = status;
        this.message = message;
        this.errors = null;
    }

    public EduProWebException(String message, HttpStatus status, List<FieldError> errors) {
        super(message);
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
   public String getMessage(){
        return message;
   }

   public Object getErrors(){
        return errors;
   }
}
