package org.edupro.web.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.web.model.request.MapelRequest;
import org.edupro.web.model.response.MapelResponse;
import org.edupro.web.model.response.Response;
import org.edupro.web.service.MasterMapelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/master/mapel")
@RequiredArgsConstructor
public class MasterMapelController extends BaseController<MapelResponse> {
    private final MasterMapelService service;

    @GetMapping
    public ModelAndView index(){
        var view = new ModelAndView("pages/master/mapel/index");
        view.addObject("data", service.get());

        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        return new ModelAndView("pages/master/mapel/add");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Integer id){
        return new ModelAndView("pages/master/mapel/edit");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id")Integer id){
        return new ModelAndView("pages/master/mapel/delete");
    }

    @GetMapping("/data")
    public ResponseEntity<Response> getData(){
        List<MapelResponse> result = service.get();
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
    public ResponseEntity<Response> save(@RequestBody @Valid MapelRequest request){
        var result = service.save(request);
        return getResponse(result);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Response> update(@RequestBody @Valid MapelRequest request, @PathVariable("id")Integer id){
        var result = service.update(request, id);

        return getResponse(result);
    }

    @PostMapping("/remove/{id}")
    public ResponseEntity<Response> remove(@PathVariable("id") Integer id){
        var result = service.delete(id);

        return getResponse(result);
    }
}
