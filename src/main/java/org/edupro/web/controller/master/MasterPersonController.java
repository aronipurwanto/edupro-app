package org.edupro.web.controller.master;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.web.controller.BaseController;
import org.edupro.web.exception.EduProWebException;
import org.edupro.web.model.request.PersonRequest;
import org.edupro.web.model.response.PersonResponse;
import org.edupro.web.model.response.Response;
import org.edupro.web.service.MasterLookupService;
import org.edupro.web.service.MasterPersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/master/person")
@RequiredArgsConstructor
public class MasterPersonController extends BaseController<PersonResponse> {

    private final MasterPersonService service;
    private final MasterLookupService lookupService;

    @GetMapping
    public ModelAndView index(){
        return new ModelAndView("pages/master/person/index");
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/master/person/add");
        view.addObject("person", new PersonRequest());
        addObject(view, lookupService);
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("person") @Valid PersonRequest request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/person/add");
        view.addObject("person", request);

        if (result.hasErrors()){
            addObject(view, lookupService);
            return view;
        }

        try {
            service.save(request);
            return new ModelAndView("redirect:/master/person");
        } catch (EduProWebException e){
            addError("person", result,(List<FieldError>)e.getErrors());
            return view;
        }
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/master/person/edit");

        return getModelAndView(id, view);
    }

    private ModelAndView getModelAndView(String id, ModelAndView view) {
        PersonResponse result;
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
    public ModelAndView update(@ModelAttribute("person") @Valid PersonRequest request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/person/edit");
        view.addObject("person", request);
        if (result.hasErrors()){
            addObject(view, lookupService);
            return view;
        }

        try {
            service.update(request, request.getId()).orElse(null);
            return new ModelAndView("redirect:/master/person");
        } catch (EduProWebException e){
            addError("person", result,(List<FieldError>)e.getErrors());
            return view;
        }
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/master/person/delete");
        return getModelAndView(id, view);
    }

    @PostMapping("/remove")
    public ModelAndView delete(@ModelAttribute("pages") @Valid PersonRequest request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/person/delete");
        view.addObject("person", request);

        if (result.hasErrors()){
            addObject(view, lookupService);
            return view;
        }

        try {
            this.service.delete(request.getId()).orElse(null);
            return new ModelAndView("redirect:/master/person");
        } catch (EduProWebException e){
            addError("person", result,(List<FieldError>)e.getErrors());
            return view;
        }
    }

    @GetMapping("/data")
    public ResponseEntity<Response> getData(){
        try {
            List<PersonResponse> result = service.get();
            return getResponse(result);
        }catch (EduProWebException e){
            return getResponse(Collections.emptyList());
        }
    }
}
