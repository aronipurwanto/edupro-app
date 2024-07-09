package org.edupro.web.classroom.controller;

import lombok.RequiredArgsConstructor;
import org.edupro.web.base.controller.BaseController;
import org.edupro.web.course.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/classroom")
@RequiredArgsConstructor
public class StreamController extends BaseController {
    private final CourseService courseService;

    @GetMapping("/{id}/stream")
    public ModelAndView grade(@PathVariable String id) {
        ModelAndView view = new ModelAndView("pages/classroom/stream");
        view.addObject("courseId", id);
        return view;
    }
}
