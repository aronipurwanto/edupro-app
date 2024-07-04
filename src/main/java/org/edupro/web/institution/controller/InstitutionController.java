package org.edupro.web.institution.controller;

import jakarta.validation.Valid;
import org.edupro.web.base.controller.BaseController;
import org.edupro.web.base.model.Response;
import org.edupro.web.building.model.BuildingRes;
import org.edupro.web.exception.EduProWebException;
import org.edupro.web.institution.model.InstitutionReq;
import org.edupro.web.institution.model.InstitutionRes;
import org.edupro.web.institution.service.InstitutionService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/master/institution")
@RequiredArgsConstructor
public class InstitutionController extends BaseController<InstitutionRes> {

    private final InstitutionService service;

    @GetMapping
    public ModelAndView index(){
        var view = new ModelAndView("pages/master/institution/index");
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view =  new ModelAndView("pages/master/institution/add");

        view.addObject("institution", new InstitutionRes());
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("institution") @Valid InstitutionReq request, BindingResult result) {
        ModelAndView view = new ModelAndView("pages/master/institution/add");
        view.addObject("institution", request);
        if (result.hasErrors()){
            return view;
        }

        Optional<InstitutionRes> res;
        try {
            res = service.save(request);
        }catch (EduProWebException e){
            addError("institution", result,(List<FieldError>) e.getErrors());
            return view;
        }

        if (res.isEmpty()){
            addError("institution", result,Collections.emptyList());
        }

        return view;
    }

    @GetMapping("/data")
    public ResponseEntity<Response> getData(){
        try {
            List<InstitutionRes> result = service.get();
            return getResponse(result);
        }catch (EduProWebException e){
            return getResponse(Collections.emptyList());
        }
    }
}
