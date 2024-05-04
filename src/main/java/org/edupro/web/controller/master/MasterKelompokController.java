package org.edupro.web.controller.master;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.web.controller.BaseController;
import org.edupro.web.exception.EduProWebException;
import org.edupro.web.model.request.KelompokRequest;
import org.edupro.web.model.response.KelompokResponse;
import org.edupro.web.model.response.Response;
import org.edupro.web.service.MasterKelompokService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/master/kelompok")
@RequiredArgsConstructor
public class MasterKelompokController extends BaseController<KelompokResponse> {
    private final MasterKelompokService service;

    @GetMapping
    public ModelAndView index(){
        var view = new ModelAndView("pages/master/kelompok/index");
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/master/kelompok/add");
        view.addObject("kelompok", new KelompokRequest());
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("kelompok") @Valid KelompokRequest request, BindingResult result) {
        ModelAndView view = new ModelAndView("pages/master/kelompok/add");
        view.addObject("kelompok", service.get());

        if (result.hasErrors()){
            view.addObject("kelompok", request);
            return view;
        }

        var response = service.save(request);
        return new ModelAndView("redirect:/master/kelompok");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id) {
        ModelAndView view = new ModelAndView("pages/master/kelompok/edit");
        var result = this.service.getById(id).orElse(null);
        if (result == null){
            return new ModelAndView("pages/master/error/not-found");
        }

        view.addObject("kelompok", result);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("kelompok") @Valid KelompokRequest request, BindingResult result) {
        ModelAndView view = new ModelAndView("pages/master/kelompok/edit");
        view.addObject("kelompok", request);

        if (result.hasErrors()) {
            view.addObject("kelompok", request);
            return view;
        }

        var response = service.update(request, request.getId());
        return new ModelAndView("redirect:/master/kelompok");
    }

    @GetMapping("/data")
    public ResponseEntity<Response> getData(){
        List<KelompokResponse> result = service.get();
        return getResponse(result);
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id) {
        ModelAndView view = new ModelAndView("pages/master/kelompok/delete");
        KelompokResponse result;
        try {
            result = this.service.getById(id).orElse(null);
        }catch (EduProWebException e){
            return new ModelAndView("pages/error/modal-500");
        }

        if (result == null){
            return new ModelAndView("pages/error/modal-not-found");
        }

        view.addObject("kelompok", result);
        return view;
    }

    @PostMapping("/remove")
    public ModelAndView remove(@ModelAttribute("kelompok") @Valid KelompokRequest request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/kelompok/delete");
        if (result.hasErrors()){
            view.addObject("kelompok", request);
            return view;
        }

        var response = service.delete(request.getId()).orElse(null);
        return new ModelAndView("redirect:/master/kelompok");
    }
}
