package org.edupro.web.level.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.web.base.controller.BaseController;
import org.edupro.web.exception.EduProWebException;
import org.edupro.web.institution.model.InstitutionRes;
import org.edupro.web.base.model.Response;
import org.edupro.web.institution.service.InstitutionService;
import org.edupro.web.level.model.LevelReq;
import org.edupro.web.level.model.LevelRes;
import org.edupro.web.level.service.LevelService;
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
@RequestMapping("/master/level")
@RequiredArgsConstructor
public class LevelController extends BaseController<LevelRes> {

    private final LevelService service;
    private final InstitutionService lembagaService;

    @GetMapping
    public ModelAndView index(){
        var view = new ModelAndView("pages/master/level/index");
        view.addObject("level",service.get());

        return view;
    }
    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/master/level/add");
        view.addObject("level", new LevelReq());

        addObject(view);
        return view;
    }

    public void addObject(ModelAndView view){
        List<InstitutionRes> lembaga = this.lembagaService.get();
        view.addObject("dataLembaga", lembaga);
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("level") @Valid LevelReq request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/level/add");
        view.addObject("level", request);

        if (result.hasErrors()){
            addObject(view);
            return view;
        }

        Optional<LevelRes> res;
        try {
            res = service.save(request);
        } catch (EduProWebException e){
            addError("level", result,(List<FieldError>)e.getErrors());
            return view;
        }

        if (res.isEmpty()){
            addError("level", result,Collections.emptyList());
        }
        return view;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/master/level/edit");
        return getModelAndView(id, view);
    }

    private ModelAndView getModelAndView(String id, ModelAndView view) {
        LevelRes result;
        try {
            result = this.service.getById(id).orElse(null);
        }catch (EduProWebException e){
            return new ModelAndView("pages/error/modal-500");
        }

        if (result == null){
            return new ModelAndView("pages/error/modal-not-found");
        }

        view.addObject("level", result);
        addObject(view);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("level") @Valid LevelReq request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/level/edit");
        view.addObject("level", request);

        if (result.hasErrors()){
            addObject(view);
            return view;
        }

        Optional<LevelRes> res;
        try {
            res = service.update(request, request.getId());
        } catch (EduProWebException e){
            addError("level", result,(List<FieldError>)e.getErrors());
            return view;
        }

        if (res.isEmpty()){
            addError("level", result,Collections.emptyList());
        }
        return view;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/master/level/delete");
        return getModelAndView(id, view);
    }

    @PostMapping("/remove")
    public ModelAndView delete(@ModelAttribute("level") @Valid LevelRes request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/level/delete");
        view.addObject("level", request);

        if (result.hasErrors()){
            addObject(view);
            return view;
        }

        Optional<LevelRes> res;
        try {
            res = service.delete(request.getId());
        } catch (EduProWebException e){
            addError("level", result,(List<FieldError>)e.getErrors());
            return view;
        }

        if (res.isEmpty()){
            addError("level", result,Collections.emptyList());
        }
        return view;
    }

    @GetMapping("/data")
    public ResponseEntity<Response> getData(){
        try {
            List<LevelRes> result = service.get();
            return getResponse(result);
        }catch (EduProWebException e){
            return getResponse(Collections.emptyList());
        }
    }
}
