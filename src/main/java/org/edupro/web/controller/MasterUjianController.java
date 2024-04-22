package org.edupro.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.web.model.request.SesiRequest;
import org.edupro.web.model.request.UjianRequest;
import org.edupro.web.model.response.UjianResponse;
import org.edupro.web.model.response.Response;
import org.edupro.web.model.response.SesiResponse;
import org.edupro.web.service.MasterUjianService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/master/ujian")
@RequiredArgsConstructor
public class MasterUjianController {
    private final MasterUjianService service;
    @GetMapping
    public ModelAndView index(){
        var view = new ModelAndView("pages/master/ujian/index");
        view.addObject("data", service.getAll());
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        return new ModelAndView("pages/master/ujian/add");
    }

    @GetMapping("/data")
    public ResponseEntity<Response> getData(){
        List<UjianResponse> result = service.getAll();
        return ResponseEntity.ok().body(
                Response.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Success")
                        .data(result)
                        .total(result.size())
                        .build()
        );
    }

    @PostMapping("/save")
    public ResponseEntity<Response> save(@RequestBody @Valid UjianRequest request){
        var result = service.save(request);
        return getResponse(result);
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Integer id){
        return new ModelAndView("pages/master/ujian/edit");
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Response> update(@RequestBody @Valid UjianRequest request, @PathVariable("id") Integer id){
        var result = service.update(request, id);
        return getResponse(result);
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Integer id){
        return new ModelAndView("pages/master/ujian/delete");
    }

    @PostMapping("/remove/{id}")
    public ResponseEntity<Response> remove(@PathVariable("id") Integer id){
        var result = service.delete(id);
        return getResponse(result);
    }

    private ResponseEntity<Response> getResponse(Optional<UjianResponse> result){
        return result.isEmpty() ? ResponseEntity.badRequest().body(
                Response.builder()
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .message("Failed")
                        .data(null)
                        .total(0)
                        .build()

        ) : ResponseEntity.ok().body(
                Response.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Success")
                        .data(result)
                        .total(1)
                        .build()
        );
    }
}
