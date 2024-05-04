package org.edupro.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @GetMapping("/home")
    public ModelAndView home(){
        return new ModelAndView("pages/home/index");
    }

    @GetMapping("/dashboard")
    public ModelAndView dashboard(){
        return new ModelAndView("pages/dashboard/index");
    }

    @GetMapping("/dashboard/profile")
    public ModelAndView dashboardProfile(){
        return new ModelAndView("pages/dashboard/_profile");
    }

    @GetMapping("/dashboard/siswa")
    public ModelAndView dashboardSiswa(){
        return new ModelAndView("pages/dashboard/_siswa-list");
    }

    @GetMapping("/dashboard/course")
    public ModelAndView dashboardCourse(){
        return new ModelAndView("pages/dashboard/_course-list");
    }

    @GetMapping("/dashboard/mapel")
    public ModelAndView dashboardMapel(){
        return new ModelAndView("pages/dashboard/_mapel-list");
    }
}
