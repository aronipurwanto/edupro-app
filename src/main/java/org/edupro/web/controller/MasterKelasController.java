package org.edupro.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.web.model.request.KelasRequest;
import org.edupro.web.model.request.LevelRequest;
import org.edupro.web.model.response.KelasResponse;
import org.edupro.web.model.response.LevelResponse;
import org.edupro.web.model.response.Response;
import org.edupro.web.service.MasterKelasService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/master/kelas")
public class MasterKelasController {
    private final MasterKelasService service;
    @GetMapping
    public ModelAndView index(){
        var view = new ModelAndView("pages/master/kelas/index");
        view.addObject("dataList", service.getAll());
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        return new ModelAndView("pages/master/kelas/add");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Integer id){
        return new ModelAndView("pages/master/kelas/edit");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Integer id){
        return new ModelAndView("pages/master/kelas/delete");
    }

    @GetMapping("/data")
    public ResponseEntity<Response> getData(){
        List<KelasResponse> result = service.getAll();
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
    public ResponseEntity<Response> save(@RequestBody @Valid KelasRequest request){
        var result = service.save(request);
        return getResponse(result);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Response> update(@RequestBody @Valid KelasRequest request, @PathVariable("id") Integer id){
        var result = service.update(request, id);

        return getResponse(result);
    }

    @PostMapping("/remove/{id}")
    public ResponseEntity<Response> remove(@PathVariable("id") Integer id){
        var result = service.delete(id);

        return getResponse(result);
    }

    private ResponseEntity<Response> getResponse(Optional<KelasResponse> result){
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
