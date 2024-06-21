package org.edupro.web.student.controller;

import com.google.gson.Gson;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.web.base.controller.BaseController;
import org.edupro.web.base.model.Response;
import org.edupro.web.exception.EduProWebException;
import org.edupro.web.lookup.service.LookupService;
import org.edupro.web.student.model.StudentReq;
import org.edupro.web.student.model.StudentRes;
import org.edupro.web.student.service.StudentService;
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
@RequestMapping("/master/siswa")
@RequiredArgsConstructor
public class StudentController extends BaseController<StudentRes> {

    private final StudentService service;
    private final LookupService lookupService;
    private final Gson gson;

    @GetMapping
    public ModelAndView index(){
        return new ModelAndView("pages/master/siswa/index");
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/master/siswa/add");

        view.addObject("siswa", new StudentReq());
        addObject(view, lookupService);
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("siswa") @Valid StudentReq request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/siswa/add");
        view.addObject("siswa", request);

        if (result.hasErrors()){
            addObject(view, lookupService);
            return view;
        }

        Optional<StudentRes> res;

        try {
            res = service.save(request);
        }catch (EduProWebException e){
            addError("siswa", result,(List<FieldError>)e.getErrors());
            addObject(view, lookupService);
            return view;
        }

        if(res.isPresent()){
            view.addObject("siswa", new StudentReq());
        }else {
            addError("siswa", result,Collections.emptyList());
        }

        return view;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/master/siswa/edit");
        return getModelAndView(id, view);
    }

    private ModelAndView getModelAndView(String id, ModelAndView view) {
        StudentRes result;
        try {
            result = this.service.getById(id).orElse(null);
        }catch (EduProWebException e){
            return new ModelAndView("pages/error/modal-500");
        }

        if (result == null){
            return new ModelAndView("pages/error/modal-not-found");
        }

        view.addObject("siswa", result);
        addObject(view, lookupService);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("siswa") @Valid StudentReq request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/siswa/edit");
        view.addObject("siswa", request);

        if (result.hasErrors()) {
            addObject(view, lookupService);
            return view;
        }

        Optional<StudentRes> res;
        try {
            res = service.update(request, request.getId());
        }catch (EduProWebException e){
            addError("siswa", result,(List<FieldError>)e.getErrors());
            addObject(view, lookupService);
            return view;
        }

        if (res.isPresent()){
            view.addObject("siswa", new StudentReq());
        }else {
            addError("siswa", result,Collections.emptyList());
        }

        return view;
    }


    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/master/siswa/delete");
        return getModelAndView(id, view);
    }

    @PostMapping("/remove")
    public ModelAndView remove(@ModelAttribute("siswa") @Valid StudentReq request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/siswa/delete");
        view.addObject("siswa", request);

        if (result.hasErrors()) {
            addObject(view, lookupService);
            return view;
        }

        Optional<StudentRes> res;

        try {
            res = service.delete(request.getId());
        }catch (EduProWebException e){
            addError("siswa", result,(List<FieldError>)e.getErrors());
            addObject(view, lookupService);
            return view;
        }

        if (res.isPresent()){
            view.addObject("siswa", new StudentReq());
        }else {
            addError("siswa", result,Collections.emptyList());
        }

        return view;
    }

    @GetMapping("/data")
    public ResponseEntity<Response> getData(){
        try {
            List<StudentRes> result = service.get();
            return getResponse(result);
        }catch (EduProWebException e){
            return getResponse(Collections.emptyList());
        }
    }

}
