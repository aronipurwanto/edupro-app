package org.edupro.web.controller.master;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.web.controller.BaseController;
import org.edupro.web.exception.EduProWebException;
import org.edupro.web.model.request.GedungRequest;
import org.edupro.web.model.response.GedungResponse;
import org.edupro.web.model.response.Response;
import org.edupro.web.service.MasterGedungService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/master/gedung")
@RequiredArgsConstructor
public class MasterGedungController extends BaseController<GedungResponse> {

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
        view.addObject("gedung", request);
        if (result.hasErrors()) {
            return view;
        }
        try {
            service.save(request);
            return new ModelAndView("redirect:/master/gedung");
        }catch (EduProWebException e){
            addError("gedung", result,(List<FieldError>)e.getErrors());
            return view;
        }
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id) {
        ModelAndView view = new ModelAndView("pages/master/gedung/edit");
        return getModelAndView(id, view);
    }

    private ModelAndView getModelAndView(String id, ModelAndView view) {
        GedungResponse result;
        try {
            result = this.service.getById(id).orElse(null);
        }catch (EduProWebException e){
            return new ModelAndView("pages/error/modal-500");
        }

        if (result == null){
            return new ModelAndView("pages/error/modal-not-found");
        }

        view.addObject("gedung", result);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("gedung") @Valid GedungRequest request, BindingResult result) {
        ModelAndView view = new ModelAndView("pages/master/gedung/edit");
        view.addObject("gedung", request);
        if (result.hasErrors()) {
            return view;
        }

        try {
            service.update(request, request.getId()).orElse(null);
            return new ModelAndView("redirect:/master/gedung");
        }catch (EduProWebException e){
            addError("gedung", result,(List<FieldError>)e.getErrors());
            return view;
        }
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id) {
        ModelAndView view = new ModelAndView("pages/master/gedung/delete");
        return getModelAndView(id, view);
    }

    @PostMapping("/remove")
    public ModelAndView remove(@ModelAttribute("gedung") @Valid GedungRequest request, BindingResult result) {
        ModelAndView view = new ModelAndView("pages/master/gedung/delete");
        view.addObject("gedung", request);

        if (result.hasErrors()) {
            return view;
        }
        try {
            service.delete(request.getId()).orElse(null);
            return new ModelAndView("redirect:/master/gedung");
        }catch (EduProWebException e){
            addError("gedung", result,(List<FieldError>)e.getErrors());
            return view;
        }
    }

    @GetMapping("/data")
    public ResponseEntity<Response> getData(){
        try {
            List<GedungResponse> result = service.get();
            return getResponse(result);
        }catch (EduProWebException e){
            return getResponse(Collections.emptyList());
        }
    }
}
