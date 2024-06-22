package org.edupro.web.course.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.web.base.controller.BaseController;
import org.edupro.web.exception.EduProWebException;
import org.edupro.web.course.model.CourseReq;
import org.edupro.web.course.model.CourseRes;
import org.edupro.web.subject.model.SubjectRes;
import org.edupro.web.base.model.Response;
import org.edupro.web.course.service.CourseService;
import org.edupro.web.subject.service.SubjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/master/course")
@RequiredArgsConstructor
public class CourseController extends BaseController<CourseRes> {

    private final CourseService service;
    private final SubjectService mapelService;

    @GetMapping
    public ModelAndView index(){
        var view = new ModelAndView("pages/master/course/index");
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/master/course/add");

        view.addObject("course", new CourseReq());
        addObject(view);
        return view;
    }

    public void addObject(ModelAndView view){
        List<SubjectRes> subjectRes = this.mapelService.get();
        view.addObject("dataMapel", subjectRes);
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("course") @Valid CourseReq request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/course/add");
        view.addObject("course", request);
        if (result.hasErrors()){
            addObject(view);
            return view;
        }

        Optional<CourseRes> res;
        try {
            res = service.save(request);
        }catch (EduProWebException e){
            addError("course", result,(List<FieldError>)e.getErrors());
            return view;
        }

        if(res.isEmpty()){
            addError("course",result, Collections.emptyList());
        }
        return view;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id")String id){
        ModelAndView view = new ModelAndView("pages/master/course/edit");
        return getModelAndView(id, view);
    }

    private ModelAndView getModelAndView(String id, ModelAndView view) {
        CourseRes result;
        try {
            result = this.service.getById(id).orElse(null);
        }catch (EduProWebException e){
            return new ModelAndView("pages/error/modal-500");
        }

        if (result == null){
            return new ModelAndView("pages/error/modal-not-found");
        }

        view.addObject("course", result);
        addObject(view);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("course") @Valid CourseReq request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/course/edit");
        view.addObject("course", request);
        if (result.hasErrors()){
            addObject(view);
            return view;
        }

        Optional<CourseRes> res;
        try {
            res = this.service.update(request, request.getId());
        }catch (EduProWebException e){
            addError("course", result,(List<FieldError>)e.getErrors());
            return view;
        }

        if(res.isEmpty()){
            addError("course",result, Collections.emptyList());
        }
        return view;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id")String id){
        ModelAndView view = new ModelAndView("pages/master/course/delete");
        return getModelAndView(id, view);
    }

    @PostMapping("/remove")
    public ModelAndView remove(@ModelAttribute("course")@Valid CourseReq request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/course/delete");
        view.addObject("course", request);
        if (result.hasErrors()){
            addObject(view);
        }

        Optional<CourseRes> res;
        try{
            res = service.delete(request.getId());
        }catch (EduProWebException e){
            addError("course", result,(List<FieldError>)e.getErrors());
            return view;
        }

        if(res.isEmpty()){
            addError("course",result, Collections.emptyList());
        }
        return view;
    }

    @GetMapping("/data")
    public ResponseEntity<Response> getData(){
        try {
            List<CourseRes> result = this.service.get();
            return getResponse(result);
        }catch (EduProWebException e){
            return getResponse(Collections.emptyList());
        }
    }
}
