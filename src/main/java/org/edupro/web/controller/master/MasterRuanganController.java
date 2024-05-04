package org.edupro.web.controller.master;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.web.controller.BaseController;
import org.edupro.web.exception.EduProWebException;
import org.edupro.web.model.request.RuanganRequest;
import org.edupro.web.model.response.GedungResponse;
import org.edupro.web.model.response.Response;
import org.edupro.web.model.response.RuanganResponse;
import org.edupro.web.service.MasterGedungService;
import org.edupro.web.service.MasterRuanganService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/master/ruangan")
@RequiredArgsConstructor
public class MasterRuanganController extends BaseController<RuanganResponse> {

    private final MasterRuanganService service;
    private final MasterGedungService gedungService;

    @GetMapping
    public ModelAndView index(){
        var view = new ModelAndView("pages/master/ruangan/index");
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/master/ruangan/add");

        view.addObject("ruangan", new RuanganRequest());
        addObject(view);
        return view;
    }

    public void addObject(ModelAndView view){
        List<GedungResponse> gedung = this.gedungService.get();
        view.addObject("dataGedung", gedung);
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("ruangan") @Valid RuanganRequest request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/ruangan/add");
        view.addObject("ruangan", request);

        if (result.hasErrors()){
            addObject(view);
            return view;
        }

        try {
            var response = service.save(request);
            return new ModelAndView("redirect:/master/ruangan");
        } catch (EduProWebException e){
            addError("ruangan", result,(List<FieldError>)e.getErrors());
            return view;
        }
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/master/ruangan/edit");

        return getModelAndView(id, view);
    }

    private ModelAndView getModelAndView(String id, ModelAndView view) {
        RuanganResponse result;
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
    public ModelAndView update(@ModelAttribute("ruangan") @Valid RuanganRequest request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/ruangan/edit");
        view.addObject("ruangan", request);

        if (result.hasErrors()) {
            addObject(view);
            return view;
        }

        try {
            var response = service.update(request, request.getId()).orElse(null);
            return new ModelAndView("redirect:/master/ruangan");
        } catch (EduProWebException e){
            addError("ruangan", result,(List<FieldError>)e.getErrors());
            return view;
        }
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/master/ruangan/delete");
        return getModelAndView(id, view);
    }

    @PostMapping("/remove")
    public ModelAndView remove(@ModelAttribute("ruangan") @Valid RuanganRequest request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/ruangan/delete");
        view.addObject("ruangan", request);

        if (result.hasErrors()) {
            addObject(view);
            return view;
        }

        try {
            service.delete(request.getId()).orElse(null);
            return new ModelAndView("redirect:/master/ruangan");
        } catch (EduProWebException e){
            addError("ruangan", result,(List<FieldError>)e.getErrors());
            return view;
        }
    }

    @GetMapping("/data")
    public ResponseEntity<Response> getData(){
        try {
            List<RuanganResponse> result = service.get();
            return getResponse(result);
        }catch (EduProWebException e){
            return getResponse(Collections.emptyList());
        }
    }
}
