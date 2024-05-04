package org.edupro.web.controller.master;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/master/sekolah")
@RequiredArgsConstructor
public class MasterSekolahController {
    @GetMapping
    public ModelAndView index(){
        return new ModelAndView("pages/master/sekolah/index");
    }

    @GetMapping("/add")
    public ModelAndView add(){
        return new ModelAndView("pages/master/sekolah/add");
    }
}
