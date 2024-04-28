package org.edupro.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.web.model.request.RuanganRequest;
import org.edupro.web.model.response.GedungResponse;
import org.edupro.web.model.response.Response;
import org.edupro.web.model.response.RuanganResponse;
import org.edupro.web.service.MasterGedungService;
import org.edupro.web.service.MasterRuanganService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/master/ruangan")
@RequiredArgsConstructor
public class MasterRuanganController extends BaseController<RuanganResponse> {

    private final MasterRuanganService service;
    private final MasterGedungService gedungService;

    @GetMapping
    public ModelAndView index(){
        var view = new ModelAndView("pages/master/ruangan/index");
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/master/ruangan/add");

        view.addObject("ruangan", new RuanganRequest());
        addObject(view);
        return view;
    }

    public void addObject(ModelAndView view){
        List<GedungResponse> gedung = this.gedungService.get();
        view.addObject("dataGedung", gedung);
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("ruangan") @Valid RuanganRequest request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/ruangan/add");

        if (result.hasErrors()){
            view.addObject("ruangan", request);
            addObject(view);
            return view;
        }

        var response = service.save(request);
        return new ModelAndView("redirect:/master/ruangan");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/master/ruangan/edit");

        var result = this.service.getById(id).orElse(null);
        if (result == null){
            return new ModelAndView("pages/master/error/not-found");
        }

        view.addObject("ruangan", result);
        addObject(view);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("ruangan") @Valid RuanganRequest request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/ruangan/edit");
        if (result.hasErrors()) {
            view.addObject("ruangan", request);
            addObject(view);
            return view;
        }

        var response = service.update(request, request.getId()).orElse(null);
        return new ModelAndView("redirect:/master/ruangan");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/master/ruangan/delete");
        var result = this.service.getById(id).orElse(null);
        if (result == null){
            return new ModelAndView("pages/master/error/not-found");
        }

        view.addObject("ruangan", result);
        addObject(view);
        return view;
    }

    @PostMapping("/remove")
    public ModelAndView remove(@ModelAttribute("ruangan") @Valid RuanganRequest request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/ruangan/delete");
        if (result.hasErrors()) {
            view.addObject("ruangan", request);
            addObject(view);
            return view;
        }

        var response = service.delete(request.getId()).orElse(null);
        return new ModelAndView("redirect:/master/ruangan");
    }

    @GetMapping("/data")
    public ResponseEntity<Response> getData(){
        List<RuanganResponse> result = service.get();
        return getResponse(result);
    }
}
