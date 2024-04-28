package org.edupro.web.controller;

import org.edupro.web.model.response.KelasResponse;
import org.edupro.web.model.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public class BaseController<T> {
    public ResponseEntity<Response> getResponse(Optional<T> result){
        return result.<ResponseEntity<Response>>map(t -> ResponseEntity.ok().body(
                Response.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Success")
                        .data(t)
                        .data(t)
                        .data(t)
                        .total(1)
                        .build()
        )).orElseGet(() -> ResponseEntity.badRequest().body(
                Response.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Success")
                        .data(null)
                        .total(0)
                        .build()
        ));
    }

    public ResponseEntity<Response> getResponse(List<T> result){
        return ResponseEntity.ok().body(
                Response.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Success")
                        .data(result)
                        .total(result.size())
                        .build()
        );
    }

    public ResponseEntity<Response> getResponse(T result){
       return result == null ? ResponseEntity.ok().body(Response.builder()
               .statusCode(HttpStatus.BAD_REQUEST.value())
               .message("Success")
               .data(null)
               .total(0)
               .build()
       ):  ResponseEntity.ok().body(
                Response.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Success")
                        .data(result)
                        .total(1)
                        .build()
       );
    }
}
