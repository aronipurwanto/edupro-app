package org.edupro.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.web.model.request.KelasRequest;
import org.edupro.web.model.request.LevelRequest;
import org.edupro.web.model.response.*;
import org.edupro.web.service.MasterKelasService;
import org.edupro.web.service.MasterLembagaService;
import org.edupro.web.service.MasterLevelService;
import org.edupro.web.service.MasterRuanganService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/master/kelas")
public class MasterKelasController extends BaseController<KelasResponse> {
    private final MasterKelasService service;
    private final MasterLembagaService lembagaService;
    private final MasterRuanganService ruanganService;
    private final MasterLevelService levelService;

    @GetMapping
    public ModelAndView index(){
        var view = new ModelAndView("pages/master/kelas/index");
        view.addObject("dataList", service.getAll());
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
    }



    @PostMapping("/save")
    public ResponseEntity<Response> save(@RequestBody @Valid KelasRequest request){
        var result = service.save(request);
        return getResponse(result);
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Integer id){
        ModelAndView view = new ModelAndView("pages/master/kelas/edit");
        var result = this.service.getById(id).orElse(null);
        if (result == null) {
            return new ModelAndView("pages/master/error/not-found");
        }
        view.addObject("kelas", result);
        addObject(view);

        return view;
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Response> update(@RequestBody @Valid KelasRequest request, @PathVariable("id") Integer id){
        var result = service.update(request, id);

        return getResponse(result);
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Integer id){
        ModelAndView view = new ModelAndView("pages/master/kelas/delete");

        var result = this.service.getById(id).orElse(null);
        if (result == null) {
            return new ModelAndView("pages/master/error/not-found");
        }
        view.addObject("kelas", result);
        addObject(view);

        return view;
    }

    @PostMapping("/remove/{id}")
    public ResponseEntity<Response> remove(@PathVariable("id") Integer id){
        var result = service.delete(id);

        return this.getResponse(result);
    }

    @GetMapping("/data")
    public ResponseEntity<Response> getData(){
        List<KelasResponse> result = service.getAll();
        return getResponse(result);
    }
}
