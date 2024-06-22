package org.edupro.web.building.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.web.base.controller.BaseController;
import org.edupro.web.building.model.BuildingRes;
import org.edupro.web.building.model.RoomReq;
import org.edupro.web.building.model.RoomRes;
import org.edupro.web.building.service.BuildingService;
import org.edupro.web.building.service.RoomService;
import org.edupro.web.exception.EduProWebException;
import org.edupro.web.base.model.Response;
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
@RequestMapping("/master/ruangan")
@RequiredArgsConstructor
public class RoomController extends BaseController<RoomRes> {

    private final RoomService service;
    private final BuildingService gedungService;

    @GetMapping
    public ModelAndView index(){
        var view = new ModelAndView("pages/master/ruangan/index");
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/master/ruangan/add");

        view.addObject("ruangan", new RoomReq());
        addObject(view);
        return view;
    }

    public void addObject(ModelAndView view){
        List<BuildingRes> gedung = this.gedungService.get();
        view.addObject("dataGedung", gedung);
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("ruangan") @Valid RoomReq request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/ruangan/add");
        view.addObject("ruangan", request);
        addObject(view);

        if (result.hasErrors()){
            return view;
        }
        Optional<RoomRes> res;
        try {
            res = service.save(request);
        } catch (EduProWebException e){
            addError("ruangan", result,(List<FieldError>)e.getErrors());
            return view;
        }

        if(res.isEmpty()){
            addError("ruangan", result,Collections.emptyList());
        }
        return view;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/master/ruangan/edit");

        return getModelAndView(id, view);
    }

    private ModelAndView getModelAndView(String id, ModelAndView view) {
        RoomRes result;
        try {
            result = this.service.getById(id).orElse(null);
        }catch (EduProWebException e){
            return new ModelAndView("pages/error/modal-500");
        }

        if (result == null){
            return new ModelAndView("pages/error/modal-not-found");
        }

        view.addObject("ruangan", result);
        addObject(view);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("ruangan") @Valid RoomReq request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/ruangan/edit");
        view.addObject("ruangan", request);
        addObject(view);

        if (result.hasErrors()) {
            return view;
        }

        Optional<RoomRes> res;
        try {
            res = service.update(request, request.getId());
        } catch (EduProWebException e){
            addError("ruangan", result,(List<FieldError>)e.getErrors());
            return view;
        }

        if(res.isEmpty()){
            addError("ruangan", result,Collections.emptyList());
        }
        return view;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/master/ruangan/delete");
        return getModelAndView(id, view);
    }

    @PostMapping("/remove")
    public ModelAndView remove(@ModelAttribute("ruangan") @Valid RoomReq request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/ruangan/delete");
        view.addObject("ruangan", request);
        addObject(view);

        if (result.hasErrors()) {
            return view;
        }
        Optional<RoomRes> res;
        try {
            res = service.delete(request.getId());
        } catch (EduProWebException e){
            addError("ruangan", result,(List<FieldError>)e.getErrors());
            return view;
        }
        if(res.isEmpty()){
            addError("ruangan", result,Collections.emptyList());
        }
        return view;
    }

    @GetMapping("/data")
    public ResponseEntity<Response> getData(){
        try {
            List<RoomRes> result = service.get();
            return getResponse(result);
        }catch (EduProWebException e){
            return getResponse(Collections.emptyList());
        }
    }
}
