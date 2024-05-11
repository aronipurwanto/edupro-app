package org.edupro.web.controller;

import jakarta.validation.Valid;
import org.edupro.web.exception.EduProWebException;
import org.edupro.web.model.request.CourseRequest;
import org.edupro.web.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/classroom")
public class ClassroomController extends BaseController {
    private final CourseService courseService;
    public ClassroomController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ModelAndView classroom() {
        return new ModelAndView("pages/classroom/index");
    }

    @GetMapping("/new")
    public ModelAndView newClassroom() {
        ModelAndView view = new ModelAndView("pages/classroom/_classroom-add");
        CourseRequest course = new CourseRequest();
        view.addObject("course", course);
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("course") @Valid CourseRequest request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/classroom/_classroom-add");
        view.addObject("course", request);
        if (result.hasErrors()){
            return view;
        }

        try {
            courseService.save(request);
            return new ModelAndView("redirect:/classroom");
        }catch (EduProWebException e){
            addError("course", result,(List<FieldError>)e.getErrors());
            return view;
        }
    }

    @GetMapping("/topic/add")
    public ModelAndView addTopic() {
        return new ModelAndView("pages/classroom/_topic-add");
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
