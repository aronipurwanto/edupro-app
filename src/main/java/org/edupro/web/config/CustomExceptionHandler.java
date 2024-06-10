 package org.edupro.web.config;

 import jakarta.validation.ConstraintViolationException;
 import jakarta.validation.ValidationException;
 import lombok.RequiredArgsConstructor;
 import lombok.extern.slf4j.Slf4j;
 import org.edupro.web.constant.CommonConstant;
 import org.edupro.web.exception.EduProWebException;
 import org.edupro.web.exception.EmptyResponseBodyException;
 import org.edupro.web.model.response.Response;
 import org.edupro.web.util.CommonUtil;
 import org.springframework.http.HttpStatus;
 import org.springframework.http.ResponseEntity;
 import org.springframework.http.converter.HttpMessageNotReadableException;
 import org.springframework.validation.BindException;
 import org.springframework.validation.FieldError;
 import org.springframework.web.HttpMediaTypeNotSupportedException;
 import org.springframework.web.HttpRequestMethodNotSupportedException;
 import org.springframework.web.bind.MethodArgumentNotValidException;
 import org.springframework.web.bind.MissingRequestHeaderException;
 import org.springframework.web.bind.MissingServletRequestParameterException;
 import org.springframework.web.bind.annotation.ExceptionHandler;
 import org.springframework.web.multipart.MultipartException;
 import org.springframework.web.multipart.support.MissingServletRequestPartException;

 import java.util.HashMap;
 import java.util.Map;
 import java.util.NoSuchElementException;

 @Slf4j
 //@ControllerAdvice
 @RequiredArgsConstructor
 public class CustomExceptionHandler {

     private final CommonUtil commonUtil;

     @ExceptionHandler(value = {Exception.class})
     public ResponseEntity<Response> handleException(Exception e) {
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                 .header(CommonConstant.TRANSACTION_ID, commonUtil.getTransactionId())
                 .body(Response.builder()
                 .message("Some error occurred, please try after some time")
                 .build());
     }

     @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
     public ResponseEntity handleException(HttpRequestMethodNotSupportedException e) {
         log.info("Exception occurred ", e);
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Response.builder()
                 .statusCode(HttpStatus.BAD_REQUEST.value())
                 .message("xception occurred " + e.getMessage())
                 .build());
     }

     @ExceptionHandler(value = {HttpMessageNotReadableException.class})
     public ResponseEntity handleException(HttpMessageNotReadableException e) {
         log.info("Unreadable Input", e);
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Response.builder()
                 .statusCode(HttpStatus.BAD_REQUEST.value())
                 .message("Unreadable Input " + e.getMessage())
                 .build());
     }

     @ExceptionHandler(value = {ConstraintViolationException.class})
     public ResponseEntity handleException(ConstraintViolationException e) {
         log.info("Constraint Violated ", e);
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Response.builder()
                 .statusCode(HttpStatus.BAD_REQUEST.value())
                 .message("Invalid Input - " + e.getMessage())
                 .build());
     }

     @ExceptionHandler(value = {MethodArgumentNotValidException.class})
     public ResponseEntity<Response> handleException(MethodArgumentNotValidException e) {
         Map<String, Object> errors = new HashMap<>();
         for(FieldError error: e.getBindingResult().getFieldErrors()){

             errors.put(error.getField(),"Invalid input - "+ error.getDefaultMessage());
         }

         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Response.builder()
                 .statusCode(HttpStatus.BAD_REQUEST.value())
                 .message(errors)
                 .build());
     }

     @ExceptionHandler(value = {ValidationException.class})
     public ResponseEntity<Response> handleException(ValidationException e) {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Response.builder()
                 .statusCode(HttpStatus.BAD_REQUEST.value())
                 .message(e.getMessage())
                 .build());
     }

     @ExceptionHandler(value = {MissingServletRequestParameterException.class})
     public ResponseEntity<Response> handleException(MissingServletRequestParameterException e) {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Response.builder()
                 .statusCode(HttpStatus.BAD_REQUEST.value())
                 .message(e.getMessage())
                 .build());
     }

     @ExceptionHandler(value = {NoSuchElementException.class})
     public ResponseEntity<Response> handleException(NoSuchElementException e) {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Response.builder()
                 .statusCode(HttpStatus.BAD_REQUEST.value())
                 .message(e.getMessage())
                 .build());
     }

     @ExceptionHandler(value = {IllegalArgumentException.class})
     public ResponseEntity<Response> handleException(IllegalArgumentException e) {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Response.builder()
                 .statusCode(HttpStatus.BAD_REQUEST.value())
                 .message(e.getMessage())
                 .build());
     }

     @ExceptionHandler(value = {MissingServletRequestPartException.class})
     public ResponseEntity<Response> handleException(MissingServletRequestPartException e) {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Response.builder()
                 .statusCode(HttpStatus.BAD_REQUEST.value())
                 .message(e.getMessage())
                 .build());
     }

     @ExceptionHandler(value = {MissingRequestHeaderException.class})
     public ResponseEntity<Response> handleException(MissingRequestHeaderException e) {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Response.builder()
                 .statusCode(HttpStatus.BAD_REQUEST.value())
                 .message(e.getMessage())
                 .build());
     }

     @ExceptionHandler(value = {HttpMediaTypeNotSupportedException.class})
     public ResponseEntity<Response> handleException(HttpMediaTypeNotSupportedException e) {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Response.builder()
                 .statusCode(HttpStatus.BAD_REQUEST.value())
                 .message("Unsupported Media Type")
                 .build());
     }

     @ExceptionHandler(value = {MultipartException.class})
     public ResponseEntity<Response> handleException(MultipartException e) {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Response.builder()
                 .statusCode(HttpStatus.BAD_REQUEST.value())
                 .message("The server encountered an issue while processing the request.")
                 .build());
     }

     @ExceptionHandler(value = {EmptyResponseBodyException.class})
     public ResponseEntity<Response> handleException(EmptyResponseBodyException e) {
         return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Response.builder()
                 .statusCode(HttpStatus.NO_CONTENT.value())
                 .message(e.getMessage())
                 .build());
     }

     @ExceptionHandler(value = {BindException.class})
     public ResponseEntity<Response> handleException(BindException e) {
         Map<String, String> errors = new HashMap<>();
         for (FieldError error : e.getBindingResult().getFieldErrors()) {
             errors.put(error.getField(), error.getDefaultMessage());
         }

         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Response.builder()
                 .statusCode(HttpStatus.BAD_REQUEST.value())
                 .message(errors)
                 .build());
     }

     @ExceptionHandler(value = {EduProWebException.class})
     public ResponseEntity<Response> handleException(EduProWebException e) {
    	 log.error(e.getMessage(), e);
         if(e.getErrors() == null) {
             return ResponseEntity.status(e.getStatus()).body(Response.builder()
                     .statusCode(e.getStatus().value())
                     .message(e.getMessage())
                     .build());
         }

         return ResponseEntity.status(e.getStatus()).body(Response.builder()
                 .statusCode(e.getStatus().value())
                 .message(e.getErrors())
                 .build());
     }
 }