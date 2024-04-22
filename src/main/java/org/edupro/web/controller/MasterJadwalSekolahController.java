package org.edupro.web.controller;

import java.util.List;

import org.edupro.web.model.response.JadwalSekolahResponse;
import org.edupro.web.model.response.Response;
import org.edupro.web.service.MasterJadwalSekolahService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/master/jadwal")
@RequiredArgsConstructor
public class MasterJadwalSekolahController {
    private final MasterJadwalSekolahService service;

    @GetMapping
    public ModelAndView index(){
        var view = new ModelAndView("pages/master/jadwal/index");
        view.addObject("data",service.get());
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        return new ModelAndView("pages/master/jadwal/add");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Integer id){
        return new ModelAndView("pages/master/jadwal/add");
    }

    @GetMapping("/data")
    public ResponseEntity<Response> getData() {
        List<JadwalSekolahResponse> result = service.get();
        return ResponseEntity.ok().body(
                Response.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Success")
                        .data(result)
                        .total(result.size())
                        .build()
        );
    }
}
