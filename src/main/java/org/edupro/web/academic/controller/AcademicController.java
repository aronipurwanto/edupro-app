package org.edupro.web.academic.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.web.academic.model.AcademicYearReq;
import org.edupro.web.academic.model.AcademicYearRes;
import org.edupro.web.academic.service.AcademicService;
import org.edupro.web.base.controller.BaseController;
import org.edupro.web.base.model.Response;
import org.edupro.web.curriculum.model.CurriculumRes;
import org.edupro.web.curriculum.service.CurriculumService;
import org.edupro.web.exception.EduProWebException;
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
@RequestMapping("/master/ta")
@RequiredArgsConstructor
public class AcademicController extends BaseController<AcademicYearRes> {

    private final AcademicService service;
    private final CurriculumService kurikulumService;

    @GetMapping
    public ModelAndView index(){
        var view = new ModelAndView("pages/master/tahun/index");
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/master/tahun/add");

        view.addObject("tahunAjaran", new AcademicYearReq());
        addObject(view);
        return view;
    }

    public void addObject(ModelAndView view){
        List<CurriculumRes> gedung = this.kurikulumService.get();
        view.addObject("dataKurikulum", gedung);
    }

    @PostMapping("/save")
    public ModelAndView save (@ModelAttribute("tahunAjaran") @Valid AcademicYearReq request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/tahun/add");
        view.addObject("tahunAjaran", request);

        if (result.hasErrors()){
            addObject(view);
            return view;
        }

        Optional<AcademicYearRes> res;
        try {
            res = service.save(request);
        } catch (EduProWebException e){
            addError("tahun", result,(List<FieldError>)e.getErrors());
            return view;
        }

        if (res.isEmpty()){
            addError("tahun", result,Collections.emptyList());
        }
        return view;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/master/tahun/edit");

        return getModelAndView(id, view);
    }

    private ModelAndView getModelAndView(String id, ModelAndView view) {
        AcademicYearRes result;
        try {
            result = this.service.getById(id).orElse(null);
        }catch (EduProWebException e){
            return new ModelAndView("pages/error/modal-500");
        }

        if (result == null){
            return new ModelAndView("pages/error/modal-not-found");
        }

        view.addObject("tahun", result);
        addObject(view);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("tahunAjaran") @Valid AcademicYearReq request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/tahun/edit");
        view.addObject("tahunAjaran", request);

        if (result.hasErrors()){
            addObject(view);
            return view;
        }

        Optional<AcademicYearRes> res;
        try {
            res = service.update(request, request.getId());
        } catch (EduProWebException e){
            addError("tahun", result,(List<FieldError>)e.getErrors());
            return view;
        }

        if (res.isEmpty()){
            addError("tahun", result,Collections.emptyList());
        }
        return view;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id")String id){
        ModelAndView view = new ModelAndView("pages/master/tahun/delete");

        return getModelAndView(id, view);
    }

    @PostMapping("/remove")
    public ModelAndView remove(@ModelAttribute("tahunAjaran") @Valid AcademicYearReq request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/tahun/delete");
        view.addObject("tahunAjaran", request);

        if (result.hasErrors()){
            addObject(view);
            return view;
        }

        Optional<AcademicYearRes> res;
        try {
            res =service.delete(request.getId());
        } catch (EduProWebException e){
            addError("tahun", result,(List<FieldError>)e.getErrors());
            return view;
        }

        if (res.isEmpty()){
            addError("tahun", result,Collections.emptyList());
        }
        return view;
    }

    @GetMapping("/data")
    public ResponseEntity<Response> getData(){
        try {
            List<AcademicYearRes> result = service.get();
            return getResponse(result);
        }catch (EduProWebException e){
            return getResponse(Collections.emptyList());
        }
    }

    @GetMapping("/kurikulum/{id}")
    public ResponseEntity<Response> getDataByKurikulum(@PathVariable("id") String kurikulumId){
        try {
            List<AcademicYearRes> result = service.getByKurikulumId(kurikulumId);
            return getResponse(result);
        }catch (EduProWebException e){
            return getResponse(Collections.emptyList());
        }
    }
}
