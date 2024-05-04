package org.edupro.web.controller.master;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.web.controller.BaseController;
import org.edupro.web.exception.EduProWebException;
import org.edupro.web.model.request.TahunAjaranRequest;
import org.edupro.web.model.response.KurikulumResponse;
import org.edupro.web.model.response.Response;
import org.edupro.web.model.response.TahunAjaranResponse;
import org.edupro.web.service.MasterKurikulumService;
import org.edupro.web.service.MasterTahunAjaranService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/master/ta")
@RequiredArgsConstructor
public class MasterTahunAjaranController extends BaseController<TahunAjaranResponse> {

    private final MasterTahunAjaranService service;
    private final MasterKurikulumService kurikulumService;

    @GetMapping
    public ModelAndView index(){
        var view = new ModelAndView("pages/master/tahun/index");
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/master/tahun/add");

        view.addObject("tahunAjaran", new TahunAjaranRequest());
        addObject(view);
        return view;
    }

    public void addObject(ModelAndView view){
        List<KurikulumResponse> gedung = this.kurikulumService.get();
        view.addObject("dataKurikulum", gedung);
    }

    @PostMapping("/save")
    public ModelAndView save (@ModelAttribute("tahunAjaran") @Valid TahunAjaranRequest request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/tahun/add");
        view.addObject("tahunAjaran", request);

        if (result.hasErrors()){
            addObject(view);
            return view;
        }

        try {
            service.save(request);
            return new ModelAndView("redirect:/master/ta");
        } catch (EduProWebException e){
            addError("tahun", result,(List<FieldError>)e.getErrors());
            return view;
        }
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/master/tahun/edit");

        return getModelAndView(id, view);
    }

    private ModelAndView getModelAndView(String id, ModelAndView view) {
        TahunAjaranResponse result;
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
    public ModelAndView update(@ModelAttribute("tahunAjaran") @Valid TahunAjaranRequest request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/tahun/edit");
        view.addObject("tahunAjaran", request);

        if (result.hasErrors()){
            addObject(view);
            return view;
        }

        try {
            service.update(request, request.getId()).orElse(null);
            return new ModelAndView("redirect:/master/ta");
        } catch (EduProWebException e){
            addError("tahun", result,(List<FieldError>)e.getErrors());
            return view;
        }
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id")String id){
        ModelAndView view = new ModelAndView("pages/master/tahun/delete");

        return getModelAndView(id, view);
    }

    @PostMapping("/remove")
    public ModelAndView remove(@ModelAttribute("tahunAjaran") @Valid TahunAjaranRequest request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/tahun/delete");
        view.addObject("tahunAjaran", request);

        if (result.hasErrors()){
            addObject(view);
            return view;
        }

        try {
            service.delete(request.getId()).orElse(null);
            return new ModelAndView("redirect:/master/ta");
        } catch (EduProWebException e){
            addError("tahun", result,(List<FieldError>)e.getErrors());
            return view;
        }
    }

    @GetMapping("/data")
    public ResponseEntity<Response> getData(){
        try {
            List<TahunAjaranResponse> result = service.get();
            return getResponse(result);
        }catch (EduProWebException e){
            return getResponse(Collections.emptyList());
        }
    }

    @GetMapping("/kurikulum/{id}")
    public ResponseEntity<Response> getDataByKurikulum(@PathVariable("id") String kurikulumId){
        try {
            List<TahunAjaranResponse> result = service.getByKurikulumId(kurikulumId);
            return getResponse(result);
        }catch (EduProWebException e){
            return getResponse(Collections.emptyList());
        }
    }
}
