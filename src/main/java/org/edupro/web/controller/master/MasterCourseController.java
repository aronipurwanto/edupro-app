package org.edupro.web.controller.master;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.web.controller.BaseController;
import org.edupro.web.exception.EduProWebException;
import org.edupro.web.model.request.CourseRequest;
import org.edupro.web.model.response.CourseResponse;
import org.edupro.web.model.response.MapelResponse;
import org.edupro.web.model.response.Response;
import org.edupro.web.service.CourseService;
import org.edupro.web.service.MasterMapelService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/master/course")
@RequiredArgsConstructor
public class MasterCourseController extends BaseController<CourseResponse> {

    private final CourseService service;
    private final MasterMapelService mapelService;

    @GetMapping
    public ModelAndView index(){
        var view = new ModelAndView("pages/master/course/index");
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/master/course/add");

        view.addObject("course", new CourseRequest());
        addObject(view);
        return view;
    }

    public void addObject(ModelAndView view){
        List<MapelResponse> mapelResponse = this.mapelService.get();
        view.addObject("dataMapel", mapelResponse);
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("course") @Valid CourseRequest request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/course/add");
        view.addObject("course", request);
        if (result.hasErrors()){
            addObject(view);
            return view;
        }

        try {
            service.save(request);
            return new ModelAndView("redirect:/master/course");
        }catch (EduProWebException e){
            addError("course", result,(List<FieldError>)e.getErrors());
            return view;
        }
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id")String id){
        ModelAndView view = new ModelAndView("pages/master/course/edit");
        return getModelAndView(id, view);
    }

    private ModelAndView getModelAndView(String id, ModelAndView view) {
        CourseResponse result;
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
    public ModelAndView update(@ModelAttribute("course") @Valid CourseRequest request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/course/edit");
        view.addObject("course", request);
        if (result.hasErrors()){
            addObject(view);
            return view;
        }

        try {
            this.service.update(request, request.getId()).orElse(null);
            return new ModelAndView("redirect:/master/course");
        }catch (EduProWebException e){
            addError("course", result,(List<FieldError>)e.getErrors());
            return view;
        }
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id")String id){
        ModelAndView view = new ModelAndView("pages/master/course/delete");
        return getModelAndView(id, view);
    }

    @PostMapping("/remove")
    public ModelAndView remove(@ModelAttribute("course")@Valid CourseRequest request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/course/delete");
        view.addObject("course", request);
        if (result.hasErrors()){
            addObject(view);
        }

        try{
            service.delete(request.getId()).orElse(null);
            return new ModelAndView("redirect:/master/course");
        }catch (EduProWebException e){
            addError("course", result,(List<FieldError>)e.getErrors());
            return view;
        }
    }

    @GetMapping("/data")
    public ResponseEntity<Response> getData(){
        try {
            List<CourseResponse> result = this.service.get();
            return getResponse(result);
        }catch (EduProWebException e){
            return getResponse(Collections.emptyList());
        }
    }
}
