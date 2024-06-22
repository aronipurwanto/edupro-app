package org.edupro.web.lookup.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.web.base.controller.BaseController;
import org.edupro.web.base.model.Response;
import org.edupro.web.exception.EduProWebException;
import org.edupro.web.lookup.model.LookupReq;
import org.edupro.web.lookup.model.LookupRes;
import org.edupro.web.lookup.service.LookupService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/master/lookup")
@RequiredArgsConstructor
public class LookupController extends BaseController<LookupRes> {

    private final LookupService service;

    @GetMapping
    public ModelAndView index(){
        var view = new ModelAndView("pages/master/lookup/index");
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/master/lookup/add");
        view.addObject("lookup", new LookupReq());
        addObject(view);
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("lookup") @Valid LookupReq request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/lookup/add");
        view.addObject("lookup", request);
        addObject(view);

        if(result.hasErrors()){
            return view;
        }

        Optional<LookupRes> res;

        try {
            res = service.save(request);
        } catch (EduProWebException e){
            addError("lookup", result,(List<FieldError>)e.getErrors());
            return view;
        }

        if(res.isEmpty()){
            addError("lookup", result, Collections.emptyList());
        }
        return view;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/master/lookup/edit");
        return getModelAndView(id, view);
    }

    private ModelAndView getModelAndView(String id, ModelAndView view) {
        LookupRes result;
        try {
            result = this.service.getById(id).orElse(null);
        }catch (EduProWebException e){
            return new ModelAndView("pages/error/modal-500");
        }

        if (result == null){
            return new ModelAndView("pages/error/modal-not-found");
        }

        view.addObject("lookup", result);
        addObject(view);

        return view;
    }

    private void addObject(ModelAndView view){
        view.addObject("groups", service.getGroup());
        view.addObject("positionComparator", Comparator.comparing(LookupRes::getPosition));
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("lookup") @Valid LookupReq request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/lookup/edit");
        view.addObject("lookup", request);
        addObject(view);

        if(result.hasErrors()){
            return view;
        }

        Optional<LookupRes> res;

        try {
            res = service.update(request, request.getId());
        } catch (EduProWebException e){
            addError("lookup", result,(List<FieldError>)e.getErrors());
            return view;
        }

        if(res.isEmpty()){
            addError("lookup", result, Collections.emptyList());
        }
        return view;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/master/lookup/delete");
        return getModelAndView(id, view);
    }

    @PostMapping("/remove")
    public ModelAndView remove(@ModelAttribute("lookup") @Valid LookupReq request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/lookup/delete");
        view.addObject("lookup", request);
        addObject(view);

        if (result.hasErrors()){
            return view;
        }

        Optional<LookupRes> res;
        try {
            res = service.delete(request.getId());
        } catch (EduProWebException e){
            addError("lookup", result,(List<FieldError>)e.getErrors());
            return view;
        }

        if(res.isEmpty()){
            addError("lookup", result, Collections.emptyList());
        }
        return view;
    }

    @GetMapping("/data")
    public ResponseEntity<Response> getData(){
        try {
            List<LookupRes> result = service.get();
            return getResponse(result);
        }catch (EduProWebException e){
            return getResponse(Collections.emptyList());
        }
    }
}
