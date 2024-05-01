package org.edupro.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.web.exception.EduProWebException;
import org.edupro.web.model.request.KelasRequest;
import org.edupro.web.model.response.*;
import org.edupro.web.service.*;
import org.edupro.web.service.impl.MasterSesiServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/master/kelas")
public class MasterKelasController extends BaseController<KelasResponse> {
    private final MasterKelasService service;
    private final MasterRuanganService ruanganService;
    private final MasterLevelService levelService;
    private final MasterLembagaService lembagaService;
    private final MasterPersonService personService;
    private final MasterTahunAjaranService tahunAjaranService;
    private final MasterSesiServiceImpl sesiService;

    @GetMapping
    public ModelAndView index(){
        var view = new ModelAndView("pages/master/kelas/index");
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/master/kelas/add");

        view.addObject("kelas", new KelasRequest());
        addObject(view);
        return view;
    }

    public void addObject(ModelAndView view){
        List<LevelResponse> level = levelService.get();
        view.addObject("level", level);

        List<RuanganResponse> ruangan = ruanganService.get();
        view.addObject("ruangan", ruangan);

        List<LembagaResponse> lembaga = lembagaService.get();
        view.addObject("lembaga", lembaga);

        List<PersonResponse> person = personService.get();
        view.addObject("person", person);

        List<SesiResponse> sesi = sesiService.get();
        view.addObject("sesi", sesi);

        List<TahunAjaranResponse> tahunAjaran = tahunAjaranService.get();
        view.addObject("tahunAjaran", tahunAjaran);

    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("kelas") @Valid KelasRequest kelasRequest, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/kelas/add");
        view.addObject("kelas", kelasRequest);

        if(result.hasErrors()){
            addObject(view);
            return view;
        }

        try {
            service.save(kelasRequest);
            return new ModelAndView("redirect:/master/kelas");
        }catch (EduProWebException e){
            addError("siswa", result,(List<FieldError>)e.getErrors());
            return view;
        }
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/master/kelas/edit");
        var result = this.service.getById(id).orElse(null);
        if (result == null) {
            return new ModelAndView("pages/master/error/not-found");
        }
        view.addObject("kelas", result);
        addObject(view);

        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("kelas") @Valid KelasRequest request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/kelas/edit");
        view.addObject("kelas", request);
        if(result.hasErrors()){
            addObject(view);
            return view;
        }

        try {
            service.update(request, request.getId()).orElse(null);
            return new ModelAndView("redirect:/master/kelas");
        }catch (EduProWebException e){
            addError("siswa", result,(List<FieldError>)e.getErrors());
            return view;
        }
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/master/kelas/delete");
        var result = this.service.getById(id).orElse(null);
        if (result == null) {
            return new ModelAndView("pages/master/error/not-found");
        }
        view.addObject("kelas", result);
        addObject(view);

        return view;
    }

    @PostMapping("/remove")
    public ModelAndView remove(@ModelAttribute("kelas") @Valid KelasRequest request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/kelas/delete");
        view.addObject("kelas", request);
        if(result.hasErrors()){
            addObject(view);
            return view;
        }

        try {
            service.delete(request.getId()).orElse(null);
            return new ModelAndView("redirect:/master/kelas");
        }catch (EduProWebException e){
            addError("siswa", result,(List<FieldError>)e.getErrors());
            return view;
        }
    }

    @GetMapping("/data")
    public ResponseEntity<Response> getData(){
        List<KelasResponse> result = service.get();
        return getResponse(result);
    }
}
