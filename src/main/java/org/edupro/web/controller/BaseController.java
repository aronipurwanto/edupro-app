package org.edupro.web.controller;

import org.edupro.web.constant.CommonConstant;
import org.edupro.web.model.response.LookupResponse;
import org.edupro.web.model.response.Response;
import org.edupro.web.service.MasterLookupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.servlet.ModelAndView;

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

    static void addObject(ModelAndView view, MasterLookupService lookupService) {
        List<LookupResponse> agama = lookupService.getByGroup(CommonConstant.GROUP_AGAMA);
        view.addObject("agama", agama);

        List<LookupResponse> gender = lookupService.getByGroup(CommonConstant.GROUP_GENDER);
        view.addObject("gender", gender);

        List<LookupResponse> golDarah = lookupService.getByGroup(CommonConstant.GROUP_GOL_DARAH);
        view.addObject("golDarah", golDarah);
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

    public void addError(String object, BindingResult bindingResult, List<FieldError> fieldErrors){
        for(FieldError fieldError : fieldErrors){
            bindingResult.addError(new FieldError(object, fieldError.getField(), fieldError.getDefaultMessage()));
        }
    }
}
