package org.edupro.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
    @GetMapping()
    public ModelAndView dashboard(){
        return new ModelAndView("pages/dashboard/index");
    }

    @GetMapping("/profile")
    public ModelAndView dashboardProfile(){
        return new ModelAndView("pages/dashboard/_profile");
    }

    @GetMapping("/siswa")
    public ModelAndView dashboardSiswa(){
        return new ModelAndView("pages/dashboard/_siswa-list");
    }

    @GetMapping("/course")
    public ModelAndView dashboardCourse(){
        return new ModelAndView("pages/dashboard/_course-list");
    }

    @GetMapping("/mapel")
    public ModelAndView dashboardMapel(){
        return new ModelAndView("pages/dashboard/_mapel-list");
    }
}
