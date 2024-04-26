package org.edupro.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.web.model.request.KurikulumRequest;
import org.edupro.web.model.response.KurikulumResponse;
import org.edupro.web.model.response.Response;
import org.edupro.web.service.MasterKurikulumService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/master/kurikulum")
@RequiredArgsConstructor
public class MasterKurikulumController {
    private final MasterKurikulumService service;

    @GetMapping
    public ModelAndView index() {
        var view = new ModelAndView("pages/master/kurikulum/index");
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add() {
        var view = new ModelAndView("pages/master/kurikulum/add");
        view.addObject("kurikulum", new KurikulumRequest());
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("kurikulum") @Valid KurikulumRequest request, BindingResult result) {
        ModelAndView view = new ModelAndView("pages/master/kurikulum/add");
        if (result.hasErrors()) {
            view.addObject("kurikulum", request);
            return view;
        }

        var response = service.save(request);
        return new ModelAndView("redirect:/master/kurikulum");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id) {
        ModelAndView view = new ModelAndView("pages/master/kurikulum/edit");
        var result = this.service.getById(id).orElse(null);
        if (result == null) {
            return new ModelAndView("pages/master/error/not-found");
        }
        view.addObject("kurikulum", result);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("kurikulum") @Valid KurikulumRequest request, BindingResult result) {
        ModelAndView view = new ModelAndView("pages/master/kurikulum/edit");
        if (result.hasErrors()) {
            view.addObject("kurikulum", request);
            return view;
        }
        var response = service.update(request).orElse(null);
        return new ModelAndView("redirect:/master/kurikulum");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String kode) {
        ModelAndView view = new ModelAndView("pages/master/kurikulum/delete");
        var result = this.service.getById(kode).orElse(null);
        if (result == null) {
            return new ModelAndView("pages/master/error/not-found");
        }
        view.addObject("kurikulum", result);
        return view;
    }

    @PostMapping("/remove")
    public ModelAndView remove(@ModelAttribute("kurikulum") @Valid KurikulumRequest request, BindingResult result) {
        ModelAndView view = new ModelAndView("pages/master/kurikulum/delete");
        if (result.hasErrors()) {
            view.addObject("kurikulum", request);
            return view;
        }
        var response = service.delete(request).orElse(null);
        return new ModelAndView("redirect:/master/kurikulum");
    }



    @GetMapping("/data")
    public ResponseEntity<Response> getData(){
        List<KurikulumResponse> result = service.get();
        return ResponseEntity.ok().body(
                Response.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Success")
                        .data(result)
                        .total(result.size())
                        .build()
        );
    }

    private ResponseEntity<Response> getResponse(Optional<KurikulumResponse> result){
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
