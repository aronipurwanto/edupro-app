package org.edupro.web.classroom.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.web.base.controller.BaseController;
import org.edupro.web.classroom.service.CourseSectionService;
import org.edupro.web.course.model.CourseReq;
import org.edupro.web.course.model.CourseSectionRes;
import org.edupro.web.course.service.CourseService;
import org.edupro.web.exception.EduProWebException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/classroom")
@RequiredArgsConstructor
public class ClassroomController extends BaseController {
    private final CourseService courseService;
    private final CourseSectionService sectionService;

    @GetMapping
    public ModelAndView classroom() {
        return new ModelAndView("pages/classroom/index");
    }

    @GetMapping("/new")
    public ModelAndView newClassroom() {
        ModelAndView view = new ModelAndView("pages/classroom/_classroom-add");
        CourseReq course = new CourseReq();
        view.addObject("course", course);
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("course") @Valid CourseReq request, BindingResult result){
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

    @GetMapping("/grades")
    public ModelAndView grade() {
        return new ModelAndView("pages/classroom/_page-grade");
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
        ModelAndView view = new ModelAndView("pages/classroom/detail");
        var result = courseService.getById(id).orElse(null);
        view.addObject("course", result);
        List<CourseSectionRes> sections = sectionService.getSectionByCourseId(id);
        if (sections.isEmpty()) {
            sections = Collections.emptyList();
        }
        view.addObject("sections", sections);
        view.addObject("noUrutComparator", Comparator.comparing(CourseSectionRes::getNoUrut));
        return view;
    }
}
