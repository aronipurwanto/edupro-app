package org.edupro.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.web.model.request.GedungRequest;
import org.edupro.web.model.response.GedungResponse;
import org.edupro.web.model.response.Response;
import org.edupro.web.service.MasterGedungService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/master/gedung")
@RequiredArgsConstructor
public class MasterGedungController extends BaseController<GedungResponse>{

    private final MasterGedungService service;

    @GetMapping
    public ModelAndView index() {
        var view = new ModelAndView("pages/master/gedung/index");
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add() {
        var view = new ModelAndView("pages/master/gedung/add");
        view.addObject("gedung", new GedungRequest());
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("gedung") @Valid GedungRequest request, BindingResult result) {
        ModelAndView view = new ModelAndView("pages/master/gedung/add");
        if (result.hasErrors()) {
            view.addObject("gedung", request);
            return view;
        }

        var response = service.save(request);
        return new ModelAndView("redirect:/master/gedung");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id) {
        ModelAndView view = new ModelAndView("pages/master/gedung/edit");
        var result = this.service.getById(id).orElse(null);
        if (result == null) {
            return new ModelAndView("pages/master/error/not-found");
        }
        view.addObject("gedung", result);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("gedung") @Valid GedungRequest request, BindingResult result) {
        ModelAndView view = new ModelAndView("pages/master/gedung/edit");
        if (result.hasErrors()) {
            view.addObject("gedung", request);
            return view;
        }
        var response = service.update(request, request.getId()).orElse(null);
        return new ModelAndView("redirect:/master/gedung");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String kode) {
        ModelAndView view = new ModelAndView("pages/master/gedung/delete");
        var result = this.service.getById(kode).orElse(null);
        if (result == null) {
            return new ModelAndView("pages/master/error/not-found");
        }
        view.addObject("gedung", result);
        return view;
    }

    @PostMapping("/remove")
    public ModelAndView remove(@ModelAttribute("gedung") @Valid GedungRequest request, BindingResult result) {
        ModelAndView view = new ModelAndView("pages/master/gedung/delete");
        if (result.hasErrors()) {
            view.addObject("gedung", request);
            return view;
        }
        var response = service.delete(request.getId()).orElse(null);
        return new ModelAndView("redirect:/master/gedung");
    }



    @GetMapping("/data")
    public ResponseEntity<Response> getData(){
        List<GedungResponse> result = service.get();
        return getResponse(result);
    }
}
