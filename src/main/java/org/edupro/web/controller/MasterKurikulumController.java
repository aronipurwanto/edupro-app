package org.edupro.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.web.exception.EduProWebException;
import org.edupro.web.model.request.KurikulumRequest;
import org.edupro.web.model.response.KurikulumResponse;
import org.edupro.web.model.response.Response;
import org.edupro.web.service.MasterKurikulumService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/master/kurikulum")
@RequiredArgsConstructor
public class MasterKurikulumController  extends BaseController<KurikulumResponse> {
    private final MasterKurikulumService service;

    @GetMapping
    public ModelAndView index() {
        var view = new ModelAndView("pages/master/kurikulum/index");
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add() {
        var view = new ModelAndView("pages/master/kurikulum/add");
        view.addObject("kurikulum", new KurikulumRequest());
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("kurikulum") @Valid KurikulumRequest request, BindingResult result) {
        ModelAndView view = new ModelAndView("pages/master/kurikulum/add");
        view.addObject("kurikulum", request);
        if (result.hasErrors()) {
            return view;
        }

        try {
            service.save(request);
            return new ModelAndView("redirect:/master/kurikulum");
        } catch (EduProWebException e){
            addError("siswa", result,(List<FieldError>)e.getErrors());
            return view;
        }
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id) {
        ModelAndView view = new ModelAndView("pages/master/kurikulum/edit");
        var result = this.service.getById(id).orElse(null);
        if (result == null) {
            return new ModelAndView("pages/master/error/not-found");
        }
        view.addObject("kurikulum", result);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("kurikulum") @Valid KurikulumRequest request, BindingResult result) {
        ModelAndView view = new ModelAndView("pages/master/kurikulum/edit");
        view.addObject("kurikulum", request);
        if (result.hasErrors()) {
            return view;
        }

        try {
            service.update(request, request.getId()).orElse(null);
            return new ModelAndView("redirect:/master/kurikulum");
        } catch (EduProWebException e){
            addError("siswa", result,(List<FieldError>)e.getErrors());
            return view;
        }
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String kode) {
        ModelAndView view = new ModelAndView("pages/master/kurikulum/delete");
        var result = this.service.getById(kode).orElse(null);
        if (result == null) {
            return new ModelAndView("pages/master/error/not-found");
        }
        view.addObject("kurikulum", result);
        return view;
    }

    @PostMapping("/remove")
    public ModelAndView remove(@ModelAttribute("kurikulum") @Valid KurikulumRequest request, BindingResult result) {
        ModelAndView view = new ModelAndView("pages/master/kurikulum/delete");
        view.addObject("kurikulum", request);
        if (result.hasErrors()) {
            return view;
        }

        try {
            service.delete(request.getId()).orElse(null);
            return new ModelAndView("redirect:/master/kurikulum");
        } catch (EduProWebException e){
            addError("siswa", result,(List<FieldError>)e.getErrors());
            return view;
        }
    }



    @GetMapping("/data")
    public ResponseEntity<Response> getData(){
        List<KurikulumResponse> result = service.get();
        return getResponse(result);
    }
}
