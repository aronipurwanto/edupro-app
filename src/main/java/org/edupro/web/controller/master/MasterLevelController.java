package org.edupro.web.controller.master;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.web.controller.BaseController;
import org.edupro.web.exception.EduProWebException;
import org.edupro.web.model.request.LevelRequest;
import org.edupro.web.model.response.LembagaResponse;
import org.edupro.web.model.response.LevelResponse;
import org.edupro.web.model.response.Response;
import org.edupro.web.service.MasterLembagaService;
import org.edupro.web.service.MasterLevelService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/master/level")
@RequiredArgsConstructor
public class MasterLevelController extends BaseController<LevelResponse> {

    private final MasterLevelService service;
    private final MasterLembagaService lembagaService;

    @GetMapping
    public ModelAndView index(){
        var view = new ModelAndView("pages/master/level/index");
        view.addObject("level",service.get());

        return view;
    }
    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/master/level/add");
        view.addObject("level", new LevelRequest());

        addObject(view);
        return view;
    }

    public void addObject(ModelAndView view){
        List<LembagaResponse> lembaga = this.lembagaService.get();
        view.addObject("dataLembaga", lembaga);
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("level") @Valid LevelRequest request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/level/add");
        view.addObject("level", request);

        if (result.hasErrors()){
            addObject(view);
            return view;
        }

        try {
            service.save(request);
            return new ModelAndView("redirect:/master/level");
        } catch (EduProWebException e){
            addError("level", result,(List<FieldError>)e.getErrors());
            return view;
        }
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/master/level/edit");
        return getModelAndView(id, view);
    }

    private ModelAndView getModelAndView(String id, ModelAndView view) {
        LevelResponse result;
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
    public ModelAndView update(@ModelAttribute("level") @Valid LevelRequest request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/level/edit");
        view.addObject("level", request);

        if (result.hasErrors()){
            addObject(view);
            return view;
        }

        try {
            service.update(request, request.getId()).orElse(null);
            return new ModelAndView("redirect:/master/level");
        } catch (EduProWebException e){
            addError("level", result,(List<FieldError>)e.getErrors());
            return view;
        }
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/master/level/delete");
        return getModelAndView(id, view);
    }

    @PostMapping("/remove")
    public ModelAndView delete(@ModelAttribute("level") @Valid LevelRequest request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/level/delete");
        view.addObject("level", request);

        if (result.hasErrors()){
            addObject(view);
            return view;
        }

        try {
            service.delete(request.getId()).orElse(null);
            return new ModelAndView("redirect:/master/level");
        } catch (EduProWebException e){
            addError("level", result,(List<FieldError>)e.getErrors());
            return view;
        }
    }

    @GetMapping("/data")
    public ResponseEntity<Response> getData(){
        try {
            List<LevelResponse> result = service.get();
            return getResponse(result);
        }catch (EduProWebException e){
            return getResponse(Collections.emptyList());
        }
    }
}
