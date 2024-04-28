package org.edupro.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.web.model.request.PersonRequest;
import org.edupro.web.model.response.LookupResponse;
import org.edupro.web.model.response.PersonResponse;
import org.edupro.web.model.response.Response;
import org.edupro.web.service.MasterLookupService;
import org.edupro.web.service.MasterPersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/master/person")
@RequiredArgsConstructor
public class MasterPersonController extends BaseController<PersonResponse>{

    private final MasterPersonService service;
    private final MasterLookupService lookupService;

    @GetMapping
    public ModelAndView index(){
        var view = new ModelAndView("pages/master/person/index");
        view.addObject("person", service.get());

        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/master/person/add");
        List<LookupResponse> agama = this.lookupService.getByGroup("AGAMA");
        List<LookupResponse> gender = this.lookupService.getByGroup("GENDER");
        List<LookupResponse> golDarah = this.lookupService.getByGroup("GOL_DARAH");

        view.addObject("person", new PersonRequest());
        view.addObject("agama", agama);
        view.addObject("gender", gender);
        view.addObject("golDarah", golDarah);
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("person") @Valid PersonRequest request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/person/add");
        List<LookupResponse> agama = this.lookupService.getByGroup("AGAMA");
        List<LookupResponse> gender = this.lookupService.getByGroup("GENDER");
        List<LookupResponse> golDarah = this.lookupService.getByGroup("GOL_DARAH");

        if (result.hasErrors()){
            view.addObject("person", request);
            return view;
        }
        var response = service.save(request);
        view.addObject("agama", agama);
        view.addObject("gender", gender);
        view.addObject("golDarah", golDarah);
        return new ModelAndView("redirect:/master/person");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/master/person/edit");
        List<LookupResponse> agama = this.lookupService.getByGroup("AGAMA");
        List<LookupResponse> gender = this.lookupService.getByGroup("GENDER");
        List<LookupResponse> golDarah = this.lookupService.getByGroup("GOL_DARAH");

        var result = this.service.getById(id).orElse(null);
        if (result == null){
            return new ModelAndView("pages/master/error/not-found");
        }
        view.addObject("person", result);
        view.addObject("agama", agama);
        view.addObject("gender", gender);
        view.addObject("golDarah", golDarah);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("person") @Valid PersonRequest request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/person/edit");
        if (result.hasErrors()){
            view.addObject("person", request);
            return view;
        }
        var response = service.update(request, request.getId()).orElse(null);
        return new ModelAndView("redirect:/master/person");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/master/person/delete");
        var result = this.service.getById(id).orElse(null);
        if (result == null){
            return new ModelAndView("pages/master/error/not-found");
        }
        view.addObject("person", result);
        return view;
    }

    @PostMapping("/remove")
    public ModelAndView delete(@ModelAttribute("pages") @Valid PersonRequest request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/person/delete");
        if (result.hasErrors()){
            view.addObject("person", request);
            return view;
        }
        var response = this.service.delete(request.getId()).orElse(null);
        return new ModelAndView("redirect:/master/person");
    }

    @GetMapping("/date")
    public ResponseEntity<Response> getData(){
        List<PersonResponse> result = service.get();
        return getResponse(result);
    }
}
