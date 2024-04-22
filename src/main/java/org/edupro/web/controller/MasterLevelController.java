package org.edupro.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.web.model.request.LevelRequest;
import org.edupro.web.model.response.LevelResponse;
import org.edupro.web.model.response.Response;
import org.edupro.web.service.MasterLevelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/master/level")
@RequiredArgsConstructor
public class MasterLevelController {

    private final MasterLevelService service;

    @GetMapping
    public ModelAndView index(){
        var view = new ModelAndView("pages/master/level/index");
        view.addObject("data",service.get());

        return view;
    }
    @GetMapping("/add")
    public ModelAndView add(){
        return new ModelAndView("pages/master/level/add");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Integer id){
        return new ModelAndView("pages/master/level/edit");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Integer id){
        return new ModelAndView("pages/master/level/delete");
    }

    @GetMapping("/data")
    public ResponseEntity<Response> getData(){
        List<LevelResponse> result = service.get();
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
    public ResponseEntity<Response> save(@RequestBody @Valid LevelRequest request){
        var result = service.save(request);
        return getResponse(result);

    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Response> update(@RequestBody @Valid LevelRequest request,@PathVariable("id") Integer id){
        var result = service.update(request, id);

        return getResponse(result);
    }

    @PostMapping("/remove/{id}")
    public ResponseEntity<Response> remove(@PathVariable("id") Integer id){
        var result = service.delete(id);

        return getResponse(result);
    }

    private ResponseEntity<Response> getResponse(Optional<LevelResponse> result){
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
