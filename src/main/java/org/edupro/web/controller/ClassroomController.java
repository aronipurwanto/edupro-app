package org.edupro.web.controller;

import org.edupro.web.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/classroom")
public class ClassroomController {
    private final CourseService courseService;
    public ClassroomController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ModelAndView classroom() {
        return new ModelAndView("pages/classroom/index");
    }

    @GetMapping("/topic/add")
    public ModelAndView addTopic() {
        return new ModelAndView("pages/classroom/topic");
    }

    @GetMapping("/items")
    public ModelAndView courseItem() {
        ModelAndView view = new ModelAndView("pages/classroom/_course-item");
        var result = courseService.getByUser();
        view.addObject("courses", result);
        return view;
    }

    @GetMapping("/detail/{id}")
    public ModelAndView courseDetail(@PathVariable("id") String id) {
        var result = courseService.getById(id);
        ModelAndView view = new ModelAndView("pages/classroom/detail");
        view.addObject("course", result);
        return view;
    }
}
