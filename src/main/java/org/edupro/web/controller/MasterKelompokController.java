package org.edupro.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.web.model.request.KelompokRequest;
import org.edupro.web.model.response.KelompokResponse;
import org.edupro.web.model.response.Response;
import org.edupro.web.service.MasterKelompokService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/master/kelompok")
@RequiredArgsConstructor
public class MasterKelompokController extends BaseController<KelompokResponse> {
    private final MasterKelompokService service;

    @GetMapping
    public ModelAndView index(){
        var view = new ModelAndView("pages/master/kelompok/index");
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/master/kelompok/add");
        view.addObject("kelompok", new KelompokRequest());
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("kelompok") @Valid KelompokRequest request, BindingResult result) {
        ModelAndView view = new ModelAndView("pages/master/kelompok/add");
        view.addObject("kelompok", service.get());

        if (result.hasErrors()){
            view.addObject("kelompok", request);
            return view;
        }

        var response = service.save(request);
        return new ModelAndView("redirect:/master/kelompok");
    }

    @GetMapping("/edit/{id}/{kode}")
    public ModelAndView edit(@PathVariable("id") Integer id, @PathVariable("kode") String kode) {
        ModelAndView view = new ModelAndView("pages/master/kelompok/edit");
        var result = this.service.getById(id, kode).orElse(null);
        if (result == null){
            return new ModelAndView("pages/master/error/not-found");
        }

        view.addObject("kelompok", result);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("kelompok") @Valid KelompokRequest request, BindingResult result) {
        ModelAndView view = new ModelAndView("pages/master/kelompok/edit");
        view.addObject("kelompok", request);

        if (result.hasErrors()) {
            view.addObject("kelompok", request);
            return view;
        }

        var response = service.update(request);
        return new ModelAndView("redirect:/master/kelompok");
    }

    @GetMapping("/data")
    public ResponseEntity<Response> getData(){
        List<KelompokResponse> result = service.get();
        return getResponse(result);
    }

    @GetMapping("/delete/{id}/{kode}")
    public ModelAndView delete(@PathVariable("id") Integer id ,@PathVariable("kode") String kode) {
        ModelAndView view = new ModelAndView("pages/master/kelompok/delete");
        var result = this.service.getById(id, kode).orElse(null);
        if (result == null){
            return new ModelAndView("pages/master/error/not-found");
        }

        view.addObject("kelompok", result);
        return view;
    }

    @PostMapping("/remove")
    public ModelAndView remove(@ModelAttribute("kelompok") KelompokRequest request, @Valid BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/kelompok/delete");
        if (result.hasErrors()){
            view.addObject("kelompok", request);
            return view;
        }

        var response = service.delete(request).orElse(null);
        return new ModelAndView("redirect:/master/kelompok");
    }
}
