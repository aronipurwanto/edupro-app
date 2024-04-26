package org.edupro.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.web.model.request.LookupRequest;
import org.edupro.web.model.response.LookupResponse;
import org.edupro.web.model.response.Response;
import org.edupro.web.service.MasterLookupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

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
        view.addObject("groups", service.getGroup());
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("lookup") @Valid LookupRequest request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/lookup/add");
        view.addObject("groups", service.getGroup());

        if(result.hasErrors()){
            view.addObject("lookup", request);
            return view;
        }

        var response = service.save(request);
        return new ModelAndView("redirect:/master/lookup");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/master/lookup/edit");
        var result = this.service.getById(id).orElse(null);
        if(result == null){
            return new ModelAndView("pages/master/error/not-found");
        }

        view.addObject("lookup", result);
        view.addObject("groups", service.getGroup());
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("lookup") @Valid LookupRequest request,  BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/lookup/edit");
        view.addObject("groups", service.getGroup());

        if(result.hasErrors()){
            view.addObject("lookup", request);
            return view;
        }
        var response = service.update(request, request.getId()).orElse(null);
        return new ModelAndView("redirect:/master/lookup");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/master/lookup/delete");
        var result = this.service.getById(id).orElse(null);
        if (result == null){
            return new ModelAndView("pages/master/error/not-found");
        }
        view.addObject("lookup", result);
        return view;
    }

    @PostMapping("/remove")
    public ModelAndView remove(@ModelAttribute("lookup") @Valid LookupRequest request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/lookup/delete");
        if (result.hasErrors()){
            view.addObject("lookup", request);
            return view;
        }

        var response = service.delete(request.getId()).orElse(null);
        return new ModelAndView("redirect:/master/lookup");
    }

    @GetMapping("/data")
    public ResponseEntity<Response> getData(){
        List<LookupResponse> result = service.get();
        return getResponse(result);
    }
}
