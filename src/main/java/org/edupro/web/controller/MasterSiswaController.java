package org.edupro.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.web.constant.CommonConstant;
import org.edupro.web.model.request.SiswaRequest;
import org.edupro.web.model.response.LookupResponse;
import org.edupro.web.model.response.Response;
import org.edupro.web.model.response.SesiResponse;
import org.edupro.web.model.response.SiswaResponse;
import org.edupro.web.service.MasterLookupService;
import org.edupro.web.service.MasterSiswaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

import static org.edupro.web.constant.CommonConstant.GROUP_AGAMA;

@Controller
@RequestMapping("/master/siswa")
@RequiredArgsConstructor
public class MasterSiswaController extends BaseController<SiswaResponse>{

    private final MasterSiswaService service;
    private final MasterLookupService lookupService;

    @GetMapping
    public ModelAndView index(){
        return new ModelAndView("pages/master/siswa/index");
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/master/siswa/add");

        view.addObject("siswa", new SiswaRequest());
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
    public ModelAndView save(@ModelAttribute("siswa") @Valid SiswaRequest request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/siswa/add");
        if (result.hasErrors()){
            view.addObject("siswa", request);
            addObject(view);
            return view;
        }

        var response = service.save(request);
        return new ModelAndView("redirect:/master/siswa");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/master/siswa/edit");
        var result = this.service.getById(id).orElse(null);
        if (result == null){
            return new ModelAndView("pages/master/error/not-found");
        }

        view.addObject("siswa", result);
        addObject(view);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("siswa") @Valid SiswaRequest request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/siswa/edit");
        if (result.hasErrors()) {
            view.addObject("siswa", result);
            addObject(view);
            return view;
        }

        var response = service.update(request, request.getId()).orElse(null);
        return new ModelAndView("redirect:/master/siswa");
    }


    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/master/siswa/delete");
        var result = this.service.getById(id).orElse(null);
        if (result == null){
            return new ModelAndView("pages/master/error/not-found");
        }

        view.addObject("siswa", result);
        addObject(view);
        return view;
    }

    @PostMapping("/remove")
    public ModelAndView remove(@ModelAttribute("siswa") @Valid SiswaRequest request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/siswa/delete");
        if (result.hasErrors()) {
            view.addObject("siswa", request);
            addObject(view);
            return view;
        }

        var response = service.delete(request.getId()).orElse(null);
        return new ModelAndView("redirect:/master/siswa");
    }

    @GetMapping("/data")
    public ResponseEntity<Response> getData(){
        List<SiswaResponse> result = service.get();
        return getResponse(result);
    }

}
