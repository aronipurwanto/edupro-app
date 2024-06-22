package org.edupro.web.subject.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.web.base.controller.BaseController;
import org.edupro.web.exception.EduProWebException;
import org.edupro.web.base.model.Response;
import org.edupro.web.subject.model.SubjectReq;
import org.edupro.web.subject.model.SubjectRes;
import org.edupro.web.subject.service.SubjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/master/mapel")
@RequiredArgsConstructor
public class SubjectController extends BaseController<SubjectRes> {
    private final SubjectService service;

    @GetMapping
    public ModelAndView index(){
        var view = new ModelAndView("pages/master/mapel/index");
        view.addObject("data", service.get());

        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        var view = new ModelAndView("pages/master/mapel/add");
        view.addObject("mapel", new SubjectReq());
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("mapel") @Valid SubjectReq request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/mapel/add");
        view.addObject("mapel", request);
        if (result.hasErrors()){
            return view;
        }

        Optional<SubjectRes> res;
        try {
            res = service.save(request);
        } catch (EduProWebException e){
            addError("mapel", result,(List<FieldError>)e.getErrors());
            return view;
        }

        if(res.isEmpty()){
            addError("mapel", result,Collections.emptyList());
        }

        return view;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/master/mapel/edit");
        return getModelAndView(id, view);
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("mapel") @Valid SubjectReq request, BindingResult result) {
        ModelAndView view = new ModelAndView("pages/master/mapel/edit");
        view.addObject("mapel", request);

        if (result.hasErrors()) {
            return view;
        }

        Optional<SubjectRes> res;
        try {
            res = service.update(request, request.getId());

        } catch (EduProWebException e){
            addError("siswa", result,(List<FieldError>)e.getErrors());
            return view;
        }

        if(res.isEmpty()){
            addError("siswa", result,Collections.emptyList());
        }

        return view;
    }


    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id) {
        ModelAndView view = new ModelAndView("pages/master/mapel/delete");
        return getModelAndView(id, view);
    }

    private ModelAndView getModelAndView(String id, ModelAndView view) {
        SubjectRes result;
        try {
            result = this.service.getById(id).orElse(null);
        }catch (EduProWebException e){
            return new ModelAndView("pages/error/modal-500");
        }

        if (result == null){
            return new ModelAndView("pages/error/modal-not-found");
        }

        view.addObject("mapel", result);
        return view;
    }

    @PostMapping("/remove")
    public ModelAndView remove(@ModelAttribute("mapel") @Valid SubjectReq request, BindingResult result) {
        ModelAndView view = new ModelAndView("pages/master/mapel/delete");
        view.addObject("mapel", request);
        if (result.hasErrors()) {
            return view;
        }

        Optional<SubjectRes> res;
        try {
            res = service.delete(request.getId());

        } catch (EduProWebException e){
            addError("mapel", result,(List<FieldError>)e.getErrors());
            return view;
        }

        if(res.isEmpty()){
            addError("mapel", result,Collections.emptyList());
        }

        return view;
    }
    @GetMapping("/data")
    public ResponseEntity<Response> getData(){
        try {
            List<SubjectRes> result = service.get();
            return getResponse(result);
        }catch (EduProWebException e){
            return getResponse(Collections.emptyList());
        }
    }
}
