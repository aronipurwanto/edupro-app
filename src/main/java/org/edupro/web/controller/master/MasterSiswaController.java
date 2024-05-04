package org.edupro.web.controller.master;

import com.google.gson.Gson;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.web.controller.BaseController;
import org.edupro.web.exception.EduProWebException;
import org.edupro.web.model.request.SiswaRequest;
import org.edupro.web.model.response.Response;
import org.edupro.web.model.response.SiswaResponse;
import org.edupro.web.service.MasterLookupService;
import org.edupro.web.service.MasterSiswaService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/master/siswa")
@RequiredArgsConstructor
public class MasterSiswaController extends BaseController<SiswaResponse> {

    private final MasterSiswaService service;
    private final MasterLookupService lookupService;
    private final Gson gson;

    @GetMapping
    public ModelAndView index(){
        return new ModelAndView("pages/master/siswa/index");
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/master/siswa/add");

        view.addObject("siswa", new SiswaRequest());
        addObject(view, lookupService);
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("siswa") @Valid SiswaRequest request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/siswa/add");
        view.addObject("siswa", request);

        if (result.hasErrors()){
            addObject(view, lookupService);
            return view;
        }

        try {
            service.save(request);
            return new ModelAndView("redirect:/master/siswa");
        }catch (EduProWebException e){
            addError("siswa", result,(List<FieldError>)e.getErrors());
            addObject(view, lookupService);
            return view;
        }
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/master/siswa/edit");
        return getModelAndView(id, view);
    }

    private ModelAndView getModelAndView(String id, ModelAndView view) {
        SiswaResponse result;
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
    public ModelAndView update(@ModelAttribute("siswa") @Valid SiswaRequest request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/siswa/edit");
        view.addObject("siswa", request);

        if (result.hasErrors()) {
            addObject(view, lookupService);
            return view;
        }

        try {
            service.update(request, request.getId()).orElse(null);
            return new ModelAndView("redirect:/master/siswa");
        }catch (EduProWebException e){
            addError("siswa", result,(List<FieldError>)e.getErrors());
            addObject(view, lookupService);
            return view;
        }
    }


    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/master/siswa/delete");
        return getModelAndView(id, view);
    }

    @PostMapping("/remove")
    public ModelAndView remove(@ModelAttribute("siswa") @Valid SiswaRequest request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/siswa/delete");
        view.addObject("siswa", request);

        if (result.hasErrors()) {
            addObject(view, lookupService);
            return view;
        }

        try {
            service.delete(request.getId()).orElse(null);
            return new ModelAndView("redirect:/master/siswa");
        }catch (EduProWebException e){
            addError("siswa", result,(List<FieldError>)e.getErrors());
            addObject(view, lookupService);
            return view;
        }
    }

    @GetMapping("/data")
    public ResponseEntity<Response> getData(){
        try {
            List<SiswaResponse> result = service.get();
            return getResponse(result);
        }catch (EduProWebException e){
            return getResponse(Collections.emptyList());
        }
    }

}
