package org.edupro.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.web.model.request.LevelRequest;
import org.edupro.web.model.response.LevelResponse;
import org.edupro.web.model.response.Response;
import org.edupro.web.service.MasterLevelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/master/level")
@RequiredArgsConstructor
public class MasterLevelController extends BaseController<LevelResponse> {

    private final MasterLevelService service;

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
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("level") @Valid LevelRequest request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/level/add");
        if (result.hasErrors()){
            view.addObject("level", request);
            return view;
        }
        var response = service.save(request);
        return new ModelAndView("redirect:/master/level");
    }

    @GetMapping("/edit/{id}/{kode}")
    public ModelAndView edit(@PathVariable("id") String id, @PathVariable("kode") String kode){
        ModelAndView view = new ModelAndView("pages/master/level/edit");
        var result = this.service.getById(id, kode).orElse(null);
        if (result == null){
            return new ModelAndView("pages/master/error/not-found");
        }
        view.addObject("level", result);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("level") @Valid LevelRequest request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/level/edit");
        if (result.hasErrors()){
            view.addObject("level", request);
            return view;
        }
        var response = service.update(request, request.getId()).orElse(null);
        return new ModelAndView("redirect:/master/level");
    }

    @GetMapping("/delete/{id}/{kode}")
    public ModelAndView delete(@PathVariable("id") String id, @PathVariable("kode") String kode){
        ModelAndView view = new ModelAndView("pages/master/level/delete");
        var result = this.service.getById(id, kode).orElse(null);
        if (result == null){
            return new ModelAndView("pages/master/error/not-found");
        }
        view.addObject("level", result);
        return view;
    }

    @PostMapping("/remove")
    public ModelAndView delete(@ModelAttribute("level") @Valid LevelRequest request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/level/delete");
        if (result.hasErrors()){
            view.addObject("level", request);
            return view;
        }
        var response = service.delete(request.getId(), request).orElse(null);
        return new ModelAndView("redirect:/master/level");
    }

    @GetMapping("/data")
    public ResponseEntity<Response> getData(){
        List<LevelResponse> result = service.get();
        return getResponse(result);
    }
}
