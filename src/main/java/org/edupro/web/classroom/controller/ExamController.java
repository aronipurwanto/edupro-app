package org.edupro.web.classroom.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.web.base.controller.BaseController;
import org.edupro.web.base.model.Response;
import org.edupro.web.classroom.model.ExamReq;
import org.edupro.web.classroom.model.ExamRes;
import org.edupro.web.classroom.service.ExamService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/master/ujian")
@RequiredArgsConstructor
public class ExamController extends BaseController<ExamRes> {
    private final ExamService service;
    @GetMapping
    public ModelAndView index(){
        var view = new ModelAndView("pages/master/ujian/index");
        view.addObject("data", service.getAll());
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        return new ModelAndView("pages/master/ujian/add");
    }

    @GetMapping("/data")
    public ResponseEntity<Response> getData(){
        List<ExamRes> result = service.getAll();
        return getResponse(result);
    }

    @PostMapping("/save")
    public ResponseEntity<Response> save(@RequestBody @Valid ExamReq request){
        var result = service.save(request);
        return getResponse(result);
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Integer id){
        return new ModelAndView("pages/master/ujian/edit");
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Response> update(@RequestBody @Valid ExamReq request, @PathVariable("id") Integer id){
        var result = service.update(request, id);
        return getResponse(result);
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Integer id){
        return new ModelAndView("pages/master/ujian/delete");
    }

    @PostMapping("/remove/{id}")
    public ResponseEntity<Response> remove(@PathVariable("id") Integer id){
        var result = service.delete(id);
        return getResponse(result);
    }
}
