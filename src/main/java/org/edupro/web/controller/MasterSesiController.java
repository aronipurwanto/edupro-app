package org.edupro.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.web.model.request.SesiRequest;
import org.edupro.web.model.response.KurikulumResponse;
import org.edupro.web.model.response.Response;
import org.edupro.web.model.response.SesiResponse;
import org.edupro.web.model.response.TahunAjaranResponse;
import org.edupro.web.service.MasterKurikulumService;
import org.edupro.web.service.MasterSesiService;
import org.edupro.web.service.TahunAjaranService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/master/sesi")
@RequiredArgsConstructor
public class MasterSesiController extends BaseController<SesiResponse>{
    private final MasterSesiService service;
    private final TahunAjaranService tahunAjaranService;
    private final MasterKurikulumService kurikulumService;

    @GetMapping
    public ModelAndView index(){
        var view = new ModelAndView("pages/master/sesi/index");
        view.addObject("data", service.get());
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/master/sesi/add");
        List<TahunAjaranResponse> tahunAjaran = this.tahunAjaranService.get();
        List<KurikulumResponse> kurikulum = this.kurikulumService.get();

        view.addObject("sesi", new SesiRequest());
        view.addObject("dataTa", tahunAjaran);
        view.addObject("kurikulum", kurikulum);
        return view;
    }

    @GetMapping("/data")
    public ResponseEntity<Response> getData(){
        List<SesiResponse> result = service.get();
        return getResponse(result);
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("sesi") @Valid SesiRequest request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/sesi/add");
        List<TahunAjaranResponse> tahunAjaran = this.tahunAjaranService.get();
        List<KurikulumResponse> kurikulum  = this.kurikulumService.get();
        if (result.hasErrors()){
            view.addObject("sesi", request);
            return view;
        }

        view.addObject("dataTa", tahunAjaran);
        view.addObject("kurikulum", kurikulum);
        var response = service.save(request);
        return new ModelAndView("redirect:/master/sesi");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/master/sesi/edit");
        var result = service.getById(id).orElse(null);
        if (result == null){
            return new ModelAndView("pages/master/error/not-found");
        }
        view.addObject("sesi", result);
        view.addObject("dataTa", tahunAjaranService.get());
        view.addObject("kurikulum", kurikulumService.get());
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("sesi") @Valid SesiRequest request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/sesi/edit");
        List<TahunAjaranResponse> tahunAjaran = this.tahunAjaranService.get();
        List<KurikulumResponse> kurikulum  = this.kurikulumService.get();
        if (result.hasErrors()){
            view.addObject("sesi", request);
            return view;
        }
        view.addObject("dataTa", tahunAjaran);
        view.addObject("kurikulum", kurikulum);
        var response = service.update(request, request.getId()).orElse(null);
        return new ModelAndView("redirect:/master/sesi");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/master/sesi/delete");
        var result = service.getById(id).orElse(null);
        if (result == null){
            return new ModelAndView("pages/master/error/not-found");
        }
        view.addObject("sesi", result);
        view.addObject("dataTa", tahunAjaranService.get());
        view.addObject("kurikulum", kurikulumService.get());
        return view;
    }

    @PostMapping("/remove")
    public ModelAndView remove(@ModelAttribute("sesi") @Valid SesiRequest request, BindingResult result) {
        ModelAndView view = new ModelAndView("pages/master/sesi/delete");
        List<TahunAjaranResponse> tahunAjaran = this.tahunAjaranService.get();
        List<KurikulumResponse> kurikulum = this.kurikulumService.get();
        if (result.hasErrors()) {
            view.addObject("sesi", request);
            return view;
        }
        view.addObject("dataTa", tahunAjaran);
        view.addObject("kurikulum", kurikulum);
        var response = service.delete(request.getId()).orElse(null);
        return new ModelAndView("redirect:/master/sesi");
    }

}
