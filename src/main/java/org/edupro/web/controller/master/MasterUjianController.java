package org.edupro.web.controller.master;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.web.controller.BaseController;
import org.edupro.web.model.request.UjianRequest;
import org.edupro.web.model.response.UjianResponse;
import org.edupro.web.model.response.Response;
import org.edupro.web.service.MasterUjianService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/master/ujian")
@RequiredArgsConstructor
public class MasterUjianController extends BaseController<UjianResponse> {
    private final MasterUjianService service;
    @GetMapping
    public ModelAndView index(){
        var view = new ModelAndView("pages/master/ujian/index");
        view.addObject("data", service.getAll());
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        return new ModelAndView("pages/master/ujian/add");
    }

    @GetMapping("/data")
    public ResponseEntity<Response> getData(){
        List<UjianResponse> result = service.getAll();
        return getResponse(result);
    }

    @PostMapping("/save")
    public ResponseEntity<Response> save(@RequestBody @Valid UjianRequest request){
        var result = service.save(request);
        return getResponse(result);
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Integer id){
        return new ModelAndView("pages/master/ujian/edit");
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Response> update(@RequestBody @Valid UjianRequest request, @PathVariable("id") Integer id){
        var result = service.update(request, id);
        return getResponse(result);
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Integer id){
        return new ModelAndView("pages/master/ujian/delete");
    }

    @PostMapping("/remove/{id}")
    public ResponseEntity<Response> remove(@PathVariable("id") Integer id){
        var result = service.delete(id);
        return getResponse(result);
    }
}
