package org.edupro.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.web.model.request.BulanRequest;
import org.edupro.web.model.response.BulanResponse;
import org.edupro.web.model.response.Response;
import org.edupro.web.model.response.RuanganResponse;
import org.edupro.web.service.MasterBulanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/master/bulan")
@RequiredArgsConstructor
public class MasterBulanController {
    private final MasterBulanService service;
    @GetMapping
    public ModelAndView index(){
        var view = new ModelAndView("pages/master/bulan/index");
        view.addObject("data", service.get());

        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        var view = new ModelAndView("pages/master/bulan/add");
        return view;
    }

    @PostMapping("/save")
    public ResponseEntity<Response> save(@RequestBody @Valid BulanRequest request){
        var result = service.save(request);
        return getResponse(result);
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Integer id){
        return new ModelAndView("pages/master/bulan/edit");
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Response> update(@RequestBody @Valid BulanRequest request,@PathVariable("id") Integer id){
        var result = service.update(request, id);

        return getResponse(result);
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Integer id){
        return new ModelAndView("pages/master/bulan/delete");
    }

    @PostMapping("/remove/{id}")
    public ResponseEntity<Response> remove(@PathVariable("id") Integer id){
        var result = service.delete(id);

        return getResponse(result);
    }

    @GetMapping("/data")
    public ResponseEntity<Response> getData(){
        List<BulanResponse> result = service.get();
        return ResponseEntity.ok().body(
                Response.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Success")
                        .data(result)
                        .total(result.size())
                        .build()
        );
    }

    private ResponseEntity<Response> getResponse(Optional<BulanResponse> result){
        return result.isEmpty() ? ResponseEntity.badRequest().body(
                Response.builder()
                        .statusCode(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED.ordinal())
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
