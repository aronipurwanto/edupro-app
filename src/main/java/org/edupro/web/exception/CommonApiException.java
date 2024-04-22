package org.edupro.web.exception;

import org.springframework.http.HttpStatus;
public class CommonApiException extends RuntimeException {
    private final HttpStatus status;
    private final String message;
    private final Object errors;

    public CommonApiException(HttpStatus status, String message, Object errors) {
        super(message);
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public CommonApiException(String message, HttpStatus status) {
        super(message);
        this.status = status;
        this.message = message;
        this.errors = null;
    }

    public CommonApiException(String message, HttpStatus status, Object errors) {
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
