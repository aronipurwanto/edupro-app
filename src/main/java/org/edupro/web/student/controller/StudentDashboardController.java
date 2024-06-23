package org.edupro.web.student.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentDashboardController {
    @GetMapping
    public ModelAndView index() {
        return new ModelAndView("pages/student/index");
    }

    @GetMapping("/classroom")
    public ModelAndView classroom() {
        return new ModelAndView("pages/student/classroom");
    }

    @GetMapping("/calendar")
    public ModelAndView calendar() {
        return new ModelAndView("pages/student/calendar");
    }

    @GetMapping("/evaluation")
    public ModelAndView evaluation() {
        return new ModelAndView("pages/student/evaluation");
    }

    @GetMapping("/schedule")
    public ModelAndView schedule() {
        return new ModelAndView("pages/student/schedule");
    }

    @GetMapping("/materials")
    public ModelAndView materials() {
        return new ModelAndView("pages/student/materials");
    }

    @GetMapping("/absent")
    public ModelAndView absent() {
        return new ModelAndView("pages/student/absent");
    }
}
