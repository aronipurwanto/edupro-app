package org.edupro.web.curriculum.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.web.base.controller.BaseController;
import org.edupro.web.curriculum.model.CurriculumReq;
import org.edupro.web.curriculum.model.CurriculumRes;
import org.edupro.web.curriculum.service.CurriculumService;
import org.edupro.web.exception.EduProWebException;
import org.edupro.web.base.model.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/master/kurikulum")
@RequiredArgsConstructor
public class CurriculumController extends BaseController<CurriculumRes> {
    private final CurriculumService service;

    @GetMapping
    public ModelAndView index() {
        var view = new ModelAndView("pages/master/kurikulum/index");
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add() {
        var view = new ModelAndView("pages/master/kurikulum/add");
        view.addObject("kurikulum", new CurriculumReq());
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("kurikulum") @Valid CurriculumReq request, BindingResult result) {
        ModelAndView view = new ModelAndView("pages/master/kurikulum/add");
        view.addObject("kurikulum", request);
        if (result.hasErrors()) {
            return view;
        }

        try {
            service.save(request);
            return new ModelAndView("redirect:/master/kurikulum");
        } catch (EduProWebException e){
            addError("kurikulum", result,(List<FieldError>)e.getErrors());
            return view;
        }
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id) {
        ModelAndView view = new ModelAndView("pages/master/kurikulum/edit");
        return getModelAndView(id, view);
    }

    private ModelAndView getModelAndView(String id, ModelAndView view) {
        CurriculumRes result;
        try {
            result = this.service.getById(id).orElse(null);
        }catch (EduProWebException e){
            return new ModelAndView("pages/error/modal-500");
        }

        if (result == null){
            return new ModelAndView("pages/error/modal-not-found");
        }

        view.addObject("kurikulum", result);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("kurikulum") @Valid CurriculumReq request, BindingResult result) {
        ModelAndView view = new ModelAndView("pages/master/kurikulum/edit");
        view.addObject("kurikulum", request);
        if (result.hasErrors()) {
            return view;
        }

        try {
            service.update(request, request.getId()).orElse(null);
            return new ModelAndView("redirect:/master/kurikulum");
        } catch (EduProWebException e){
            addError("kurikulum", result,(List<FieldError>)e.getErrors());
            return view;
        }
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id) {
        ModelAndView view = new ModelAndView("pages/master/kurikulum/delete");
        return getModelAndView(id, view);
    }

    @PostMapping("/remove")
    public ModelAndView remove(@ModelAttribute("kurikulum") @Valid CurriculumReq request, BindingResult result) {
        ModelAndView view = new ModelAndView("pages/master/kurikulum/delete");
        view.addObject("kurikulum", request);
        if (result.hasErrors()) {
            return view;
        }

        try {
            service.delete(request.getId()).orElse(null);
            return new ModelAndView("redirect:/master/kurikulum");
        } catch (EduProWebException e){
            addError("kurikulum", result,(List<FieldError>)e.getErrors());
            return view;
        }
    }



    @GetMapping("/data")
    public ResponseEntity<Response> getData(){
        try {
            List<CurriculumRes> result = service.get();
            return getResponse(result);
        }catch (EduProWebException e){
            return getResponse(Collections.emptyList());
        }
    }
}
