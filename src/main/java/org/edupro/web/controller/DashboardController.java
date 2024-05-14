package org.edupro.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
    @GetMapping()
    public ModelAndView dashboard(){
        return new ModelAndView("pages/dashboard/index");
    }

    @GetMapping("/pages/{page}")
    public ModelAndView getPage(@PathVariable String page){
        if(page.equals("profile")){
            return dashboardProfile();
        }

        if(page.equals("siswa")){
            return dashboardSiswa();
        }

        if(page.equals("course")){
            return dashboardCourse();
        }

        if(page.equals("mapel")){
            return dashboardMapel();
        }

        return new ModelAndView("redirect:/dashboard");
    }


    private ModelAndView dashboardProfile(){
        return new ModelAndView("pages/dashboard/_profile");
    }


    private ModelAndView dashboardSiswa(){
        return new ModelAndView("pages/dashboard/_siswa-list");
    }


    private ModelAndView dashboardCourse(){
        return new ModelAndView("pages/dashboard/_course-list");
    }

    public ModelAndView dashboardMapel(){
        return new ModelAndView("pages/dashboard/_mapel-list");
    }
}
