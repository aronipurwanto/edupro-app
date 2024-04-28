package org.edupro.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.web.constant.CommonConstant;
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
        return new ModelAndView("pages/master/person/index");
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/master/person/add");
        view.addObject("person", new PersonRequest());
        addObject(view);
        return view;
    }

    public void addObject(ModelAndView view){
        List<LookupResponse> agama = lookupService.getByGroup(CommonConstant.GROUP_AGAMA);
        view.addObject("agama", agama);

        List<LookupResponse> gender = lookupService.getByGroup(CommonConstant.GROUP_GENDER);
        view.addObject("gender", gender);

        List<LookupResponse> golDarah = lookupService.getByGroup(CommonConstant.GROUP_GOL_DARAH);
        view.addObject("golDarah", golDarah);
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("person") @Valid PersonRequest request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/person/add");
        if (result.hasErrors()){
            view.addObject("person", request);
            addObject(view);
            return view;
        }
        var response = service.save(request);
        return new ModelAndView("redirect:/master/person");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/master/person/edit");

        var result = this.service.getById(id).orElse(null);
        if (result == null){
            return new ModelAndView("pages/master/error/not-found");
        }
        view.addObject("person", result);
        view.addObject(view);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("person") @Valid PersonRequest request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/person/edit");
        if (result.hasErrors()){
            view.addObject("person", request);
            addObject(view);
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
        addObject(view);
        return view;
    }

    @PostMapping("/remove")
    public ModelAndView delete(@ModelAttribute("pages") @Valid PersonRequest request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/person/delete");
        if (result.hasErrors()){
            view.addObject("person", request);
            addObject(view);
            return view;
        }
        var response = this.service.delete(request.getId()).orElse(null);
        return new ModelAndView("redirect:/master/person");
    }

    @GetMapping("/data")
    public ResponseEntity<Response> getData(){
        List<PersonResponse> result = service.get();
        return getResponse(result);
    }
}
