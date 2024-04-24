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

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Integer id){
        ModelAndView view = new ModelAndView("pages/master/lookup/edit");
        var result = this.service.getById(id).orElse(null);
        if(result == null){
            return new ModelAndView("pages/master/error/not-found");
        }

        view.addObject("lookup", result);
        view.addObject("groups", service.getGroup());
        return view;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Integer id){
        return new ModelAndView("pages/master/lookup/delete");
    }

    @GetMapping("/data")
    public ResponseEntity<Response> getData(){
        List<LookupResponse> result = service.get();
        return getResponse(result);
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

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("lookup") @Valid LookupRequest request,  BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/lookup/edit");
        view.addObject("groups", service.getGroup());

        if(result.hasErrors()){
            view.addObject("lookup", request);
            return view;
        }
        var response = service.update(request).orElse(null);
        return new ModelAndView("redirect:/master/lookup");
    }

    @PostMapping("/remove/{id}")
    public ResponseEntity<Response> remove(@PathVariable("id") Integer id){
        var result = service.delete(id);

        return getResponse(result);
    }
}
