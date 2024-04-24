package org.edupro.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.web.model.request.SiswaRequest;
import org.edupro.web.model.response.Response;
import org.edupro.web.model.response.SiswaResponse;
import org.edupro.web.service.MasterSiswaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/master/siswa")
@RequiredArgsConstructor
public class MasterSiswaController extends BaseController<SiswaResponse{

    private final MasterSiswaService service;

    @GetMapping
    public ModelAndView index(){
        return new ModelAndView("pages/master/siswa/index");
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/master/siswa/add");

        view.addObject("siswa", new SiswaRequest());
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("siswa") @Valid SiswaRequest request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/siswa/add");
        if (result.hasErrors()){
            view.addObject("siswa", request);
            return view;
        }

        var response = service.save(request);
        return new ModelAndView("redirect:/master/siswa");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/master/siswa/edit");
        var result = this.service.getById(id).orElse(null);
        if (result == null){
            return new ModelAndView("pages/master/error/not-found");
        }

        view.addObject("siswa", result);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("siswa") @Valid SiswaRequest request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/siswa/edit");
        if (result.hasErrors()) {
            view.addObject("siswa", request);
            return view;
        }

        var response = service.update(request).orElse(null);
        return new ModelAndView("redirect:/master/siswa");
    }


    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/master/siswa/delete");
        var result = this.service.getById(id).orElse(null);
        if (result == null){
            return new ModelAndView("pages/master/error/not-found");
        }

        view.addObject("ruangan", result);
        return view;
    }

    @PostMapping("/remove/{id}")
    public ResponseEntity<Response> remove(@ModelAttribute("siswa") String id){
        var result = service.delete(id);

        return getResponse(result);
    }

    @GetMapping("/data")
    public ResponseEntity<Response> getData(){
        List<SiswaResponse> result = service.get();
        return getResponse(result);
    }

}
