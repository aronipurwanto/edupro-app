package org.edupro.web.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.web.model.request.TahunAjaranRequest;
import org.edupro.web.model.response.Response;
import org.edupro.web.model.response.TahunAjaranResponse;
import org.edupro.web.service.TahunAjaranService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/master/tahun")
@RequiredArgsConstructor
public class TahunAjaranController extends BaseController<TahunAjaranResponse> {

    private final TahunAjaranService service;

    @GetMapping
    public ModelAndView index(){
        var view = new ModelAndView("pages/master/tahun/index");
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/master/tahun/add");

        view.addObject("tahunAjaran", new TahunAjaranRequest());
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save (@ModelAttribute("tahunAjaran") @Valid TahunAjaranRequest request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/tahun/add");

        if (result.hasErrors()){
            view.addObject("tahunAjaran", request);
            return view;
        }

        var response = service.save(request);
        return new ModelAndView("redirect:/master/tahun");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/master/tahun/edit");
        var result = this.service.getById(id).orElse(null);
        if (result == null){
            return new ModelAndView("pages/master/error/not-found");
        }

        view.addObject("tahunAjaran", result);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("tahunAjaran") @Valid TahunAjaranRequest request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/tahun/edit");
        if (result.hasErrors()){
            view.addObject("tahunAjaran", request);
            return view;
        }

        var response = service.update(request, request.getId()).orElse(null);
        return new ModelAndView("redirect:/master/tahun");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id")String id){
        ModelAndView view = new ModelAndView("pages/master/tahun/delete");
        var result = this.service.getById(id).orElse(null);
        if (result == null){
            return new ModelAndView("pages/master/error/not-found");
        }

        view.addObject("tahunAjaran", result);
        return view;
    }

    @PostMapping("/remove")
    public ModelAndView remove(@ModelAttribute("tahunAjaran") @Valid TahunAjaranRequest request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/tahun/delete");
        if (result.hasErrors()){
            view.addObject("tahunAjaran", request);
            return view;
        }

        var response = service.delete(request.getId()).orElse(null);
        return new ModelAndView("redirect:/master/tahun");
    }

    @GetMapping("/data")
    public ResponseEntity<Response> getData(){
        List<TahunAjaranResponse> result = service.get();
        return getResponse(result);
    }
}
