package org.edupro.web.classroom.controller;

import lombok.RequiredArgsConstructor;
import org.edupro.web.base.controller.BaseController;
import org.edupro.web.exception.EduProWebException;
import org.edupro.web.classroom.model.ClassScheduleRes;
import org.edupro.web.classroom.service.ClassScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/master/jadwal")
@RequiredArgsConstructor
public class ClassScheduleController extends BaseController {
    private final ClassScheduleService service;

    @GetMapping
    public ModelAndView index(){
        var view = new ModelAndView("pages/master/jadwal/index");
        view.addObject("data",service.get());
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        return new ModelAndView("pages/master/jadwal/add");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Integer id){
        return new ModelAndView("pages/master/jadwal/add");
    }

    @GetMapping("/data")
    public ResponseEntity getData() {
        try {
            List<ClassScheduleRes> result = service.get();
            return getResponse(result);
        }catch (EduProWebException e){
            return getResponse(Collections.emptyList());
        }
    }
}
