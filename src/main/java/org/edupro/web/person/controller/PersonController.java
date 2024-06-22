package org.edupro.web.person.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.web.base.controller.BaseController;
import org.edupro.web.exception.EduProWebException;
import org.edupro.web.base.model.Response;
import org.edupro.web.lookup.service.LookupService;
import org.edupro.web.person.model.PersonReq;
import org.edupro.web.person.model.PersonRes;
import org.edupro.web.person.service.PersonService;
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
@RequestMapping("/master/person")
@RequiredArgsConstructor
public class PersonController extends BaseController<PersonRes> {

    private final PersonService service;
    private final LookupService lookupService;

    @GetMapping
    public ModelAndView index(){
        return new ModelAndView("pages/master/person/index");
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/master/person/add");
        view.addObject("person", new PersonReq());
        addObject(view, lookupService);
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("person") @Valid PersonReq request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/person/add");
        view.addObject("person", request);

        if (result.hasErrors()){
            addObject(view, lookupService);
            return view;
        }

        Optional<PersonRes> res;
        try {
            res = service.save(request);
        } catch (EduProWebException e){
            addError("person", result,(List<FieldError>)e.getErrors());
            return view;
        }

        if (res.isEmpty()){
            addError("person", result,Collections.emptyList());
        }
        return view;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/master/person/edit");

        return getModelAndView(id, view);
    }

    private ModelAndView getModelAndView(String id, ModelAndView view) {
        PersonRes result;
        try {
            result = this.service.getById(id).orElse(null);
        }catch (EduProWebException e){
            return new ModelAndView("pages/error/modal-500");
        }

        if (result == null){
            return new ModelAndView("pages/error/modal-not-found");
        }

        view.addObject("person", result);
        addObject(view, lookupService);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("person") @Valid PersonReq request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/person/edit");
        view.addObject("person", request);
        if (result.hasErrors()){
            addObject(view, lookupService);
            return view;
        }

        Optional<PersonRes> res;
        try {
            res = service.update(request, request.getId());
        } catch (EduProWebException e){
            addError("person", result,(List<FieldError>)e.getErrors());
            return view;
        }

        if (res.isEmpty()){
            addError("person", result,Collections.emptyList());
        }
        return view;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/master/person/delete");
        return getModelAndView(id, view);
    }

    @PostMapping("/remove")
    public ModelAndView delete(@ModelAttribute("pages") @Valid PersonReq request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/person/delete");
        view.addObject("person", request);

        if (result.hasErrors()){
            addObject(view, lookupService);
            return view;
        }

        Optional<PersonRes> res;
        try {
            res = this.service.delete(request.getId());
        } catch (EduProWebException e){
            addError("person", result,(List<FieldError>)e.getErrors());
            return view;
        }

        if (res.isEmpty()){
            addError("person", result,Collections.emptyList());
        }
        return view;
    }

    @GetMapping("/data")
    public ResponseEntity<Response> getData(){
        try {
            List<PersonRes> result = service.get();
            return getResponse(result);
        }catch (EduProWebException e){
            return getResponse(Collections.emptyList());
        }
    }
}
