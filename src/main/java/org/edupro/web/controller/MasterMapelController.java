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
import org.springframework.validation.BindingResult;
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
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        var view = new ModelAndView("pages/master/mapel/add");
        view.addObject("mapel", new MapelRequest());
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("mapel") @Valid MapelRequest request, BindingResult result) {
        ModelAndView view = new ModelAndView("pages/master/mapel/add");
        if (result.hasErrors()) {
            view.addObject("mapel", request);
            return view;
        }

        var response = service.save(request);
        return new ModelAndView("redirect:/master/mapel");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/master/mapel/edit");
        var result = this.service.getById(id).orElse(null);
        if (result == null){
            return new ModelAndView("pages/master/error/not-found");
        }
        view.addObject("mapel", result);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("mapel") @Valid MapelRequest request, BindingResult result) {
        ModelAndView view = new ModelAndView("pages/master/gedung/edit");
        if (result.hasErrors()) {
            view.addObject("mapel", request);
            return view;
        }
        var response = service.update(request).orElse(null);
        return new ModelAndView("redirect:/master/mapel");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String kode) {
        ModelAndView view = new ModelAndView("pages/master/mapel/delete");
        var result = this.service.getById(kode).orElse(null);
        if (result == null) {
            return new ModelAndView("pages/master/error/not-found");
        }
        view.addObject("mapel", result);
        return view;
    }


    @PostMapping("/remove")
    public ModelAndView remove(@ModelAttribute("mapel") @Valid MapelRequest request, BindingResult result) {
        ModelAndView view = new ModelAndView("pages/master/mapel/delete");
        if (result.hasErrors()) {
            view.addObject("mapel", request);
            return view;
        }
        var response = service.delete(request).orElse(null);
        return new ModelAndView("redirect:/master/mapel");
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
