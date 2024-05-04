package org.edupro.web.controller.master;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.web.constant.CommonConstant;
import org.edupro.web.controller.BaseController;
import org.edupro.web.exception.EduProWebException;
import org.edupro.web.model.request.SesiRequest;
import org.edupro.web.model.response.*;
import org.edupro.web.service.MasterKurikulumService;
import org.edupro.web.service.MasterLookupService;
import org.edupro.web.service.MasterSesiService;
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
@RequestMapping("/master/sesi")
@RequiredArgsConstructor
public class MasterSesiController extends BaseController<SesiResponse> {
    private final MasterSesiService service;
    private final MasterTahunAjaranService taService;
    private final MasterKurikulumService kurikulumService;
    private final MasterLookupService lookupService;

    @GetMapping
    public ModelAndView index(){
        var view = new ModelAndView("pages/master/sesi/index");
        view.addObject("data", service.get());
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/master/sesi/add");

        view.addObject("sesi", new SesiRequest());
        addObject(view);
        return view;
    }

    private void addObject(ModelAndView view){
        List<TahunAjaranResponse> tahunAjaran = this.taService.get();
        view.addObject("dataTa", tahunAjaran);

        List<KurikulumResponse> kurikulum = this.kurikulumService.get();
        view.addObject("kurikulum", kurikulum);

        List<LookupResponse> lookup = this.lookupService.getByGroup(CommonConstant.GROUP_SEMESTER);
        view.addObject("semester", lookup);
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("sesi") @Valid SesiRequest request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/sesi/add");
        view.addObject("sesi", request);

        if (result.hasErrors()){
            addObject(view);

            return view;
        }

        try {
            service.save(request);
            return new ModelAndView("redirect:/master/sesi");
        } catch (EduProWebException e){
            addError("sesi", result,(List<FieldError>)e.getErrors());
            return view;
        }
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/master/sesi/edit");
        return getModelAndView(id, view);
    }

    private ModelAndView getModelAndView(String id, ModelAndView view) {
        SesiResponse result;
        try {
            result = this.service.getById(id).orElse(null);
        }catch (EduProWebException e){
            return new ModelAndView("pages/error/modal-500");
        }

        if (result == null){
            return new ModelAndView("pages/error/modal-not-found");
        }

        view.addObject("sesi", result);
        addObject(view);

        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("sesi") @Valid SesiRequest request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/sesi/edit");
        view.addObject("sesi", request);

        if (result.hasErrors()){
            addObject(view);
            return view;
        }

        try {
            service.update(request, request.getId()).orElse(null);
            return new ModelAndView("redirect:/master/sesi");
        } catch (EduProWebException e){
            addError("sesi", result,(List<FieldError>)e.getErrors());
            return view;
        }
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/master/sesi/delete");
        return getModelAndView(id, view);
    }

    @PostMapping("/remove")
    public ModelAndView remove(@ModelAttribute("sesi") @Valid SesiRequest request, BindingResult result) {
        ModelAndView view = new ModelAndView("pages/master/sesi/delete");
        view.addObject("sesi", request);

        if (result.hasErrors()) {
            addObject(view);
            return view;
        }

        try {
            service.delete(request.getId()).orElse(null);
            return new ModelAndView("redirect:/master/sesi");
        } catch (EduProWebException e){
            addError("sesi", result,(List<FieldError>)e.getErrors());
            return view;
        }
    }

    @GetMapping("/data")
    public ResponseEntity<Response> getData(){
        try {
            List<SesiResponse> result = service.get();
            return getResponse(result);
        }catch (EduProWebException e){
            return getResponse(Collections.emptyList());
        }
    }

}
