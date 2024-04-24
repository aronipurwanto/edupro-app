package org.edupro.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.web.model.request.RuanganRequest;
import org.edupro.web.model.response.GedungResponse;
import org.edupro.web.model.response.Response;
import org.edupro.web.model.response.RuanganResponse;
import org.edupro.web.service.MasterGedungService;
import org.edupro.web.service.MasterRuanganService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/master/ruangan")
@RequiredArgsConstructor
public class MasterRuanganController {

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
        List<GedungResponse> gedungResponses = this.gedungService.get();

        view.addObject("ruangan", new RuanganRequest());
        view.addObject("dataGedung", gedungResponses);
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("ruangan") @Valid RuanganRequest request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/ruangan/add");
        List<GedungResponse> gedungResponses = this.gedungService.get();

        if (result.hasErrors()){
            view.addObject("ruangan", request);
            return view;
        }

        view.addObject("dataGedung", gedungResponses);
        var response = service.save(request);
        return new ModelAndView("redirect:/master/ruangan");
    }

    @GetMapping("/edit/{kode}")
    public ModelAndView edit(@PathVariable("kode") String id){
        return new ModelAndView("pages/master/ruangan/edit");
    }

    @PostMapping("/update/{kode}")
    public ResponseEntity<Response> update(@RequestBody @Valid RuanganRequest request,@PathVariable("kode") String kode){
        var result = service.update(request, kode);

        return getResponse(result);
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/master/ruangan/delete");
        var result = this.service.getById(id).orElse(null);
        if (result == null){
            return new ModelAndView("pages/master/error/not-found");
        }

        view.addObject("ruangan", result);
        return view;
    }

    @PostMapping("/remove/{kode}")
    public ResponseEntity<Response> remove(@ModelAttribute("ruangan") String id){
        var result = service.delete(id);

        return getResponse(result);
    }

    @GetMapping("/data")
    public ResponseEntity<Response> getData(){
        List<RuanganResponse> result = service.get();
        return ResponseEntity.ok().body(
                Response.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Success")
                        .data(result)
                        .total(result.size())
                        .build()
        );
    }

    private ResponseEntity<Response> getResponse(Optional<RuanganResponse> result){
        return result.isEmpty() ? ResponseEntity.badRequest().body(
                Response.builder()
                        .statusCode(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED.ordinal())
                        .message("Failed")
                        .data(null)
                        .total(0)
                        .build()
        ) : ResponseEntity.ok().body(
                Response.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Success")
                        .data(result)
                        .total(1)
                        .build()
        );
    }
}
