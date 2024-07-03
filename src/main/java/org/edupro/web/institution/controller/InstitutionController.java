package org.edupro.web.institution.controller;

import org.edupro.web.base.controller.BaseController;
import org.edupro.web.base.model.Response;
import org.edupro.web.building.model.BuildingRes;
import org.edupro.web.exception.EduProWebException;
import org.edupro.web.institution.model.InstitutionRes;
import org.edupro.web.institution.service.InstitutionService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;

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
