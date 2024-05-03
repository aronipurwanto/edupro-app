package org.edupro.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.web.exception.EduProWebException;
import org.edupro.web.model.request.LookupRequest;
import org.edupro.web.model.response.LookupResponse;
import org.edupro.web.model.response.Response;
import org.edupro.web.service.MasterLookupService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/master/lookup")
@RequiredArgsConstructor
public class MasterLookupController extends BaseController<LookupResponse> {

    private final MasterLookupService service;

    @GetMapping
    public ModelAndView index(){
        var view = new ModelAndView("pages/master/lookup/index");
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/master/lookup/add");
        view.addObject("lookup", new LookupRequest());
        addObject(view);
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("lookup") @Valid LookupRequest request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/lookup/add");
        view.addObject("lookup", request);

        if(result.hasErrors()){
            addObject(view);
            return view;
        }

        try {
            service.save(request);
            return new ModelAndView("redirect:/master/lookup");
        } catch (EduProWebException e){
            addError("siswa", result,(List<FieldError>)e.getErrors());
            return view;
        }
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/master/lookup/edit");
        var result = this.service.getById(id).orElse(null);
        if(result == null){
            return new ModelAndView("pages/master/error/not-found");
        }

        view.addObject("lookup", result);
        addObject(view);

        return view;
    }

    private void addObject(ModelAndView view){
        view.addObject("groups", service.getGroup());
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("lookup") @Valid LookupRequest request,  BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/lookup/edit");
        view.addObject("lookup", request);

        if(result.hasErrors()){
            addObject(view);
            return view;
        }

        try {
            service.update(request, request.getId()).orElse(null);
            return new ModelAndView("redirect:/master/lookup");
        } catch (EduProWebException e){
            addError("siswa", result,(List<FieldError>)e.getErrors());
            return view;
        }
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/master/lookup/delete");
        var result = this.service.getById(id).orElse(null);
        if (result == null){
            return new ModelAndView("pages/master/error/not-found");
        }

        view.addObject("lookup", result);
        addObject(view);
        return view;
    }

    @PostMapping("/remove")
    public ModelAndView remove(@ModelAttribute("lookup") @Valid LookupRequest request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/lookup/delete");
        view.addObject("lookup", request);
        if (result.hasErrors()){
            addObject(view);
            return view;
        }

        try {
            service.delete(request.getId()).orElse(null);
            return new ModelAndView("redirect:/master/lookup");
        } catch (EduProWebException e){
            addError("siswa", result,(List<FieldError>)e.getErrors());
            return view;
        }
    }

    @GetMapping("/data")
    public ResponseEntity<Response> getData(){
        try {
            List<LookupResponse> result = service.get();
            return getResponse(result);
        }catch (EduProWebException e){
            return getResponse(Collections.emptyList());
        }
    }
}
