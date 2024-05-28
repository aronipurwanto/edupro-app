package org.edupro.web.controller;

import jakarta.validation.Valid;
import org.edupro.web.exception.EduProWebException;
import org.edupro.web.model.request.CoursePersonRequest;
import org.edupro.web.model.request.CourseRequest;
import org.edupro.web.model.request.CourseSectionReq;
import org.edupro.web.model.request.CourseSiswaRequest;
import org.edupro.web.model.response.*;
import org.edupro.web.service.CourseService;
import org.edupro.web.service.MasterPersonService;
import org.edupro.web.service.MasterSiswaService;
import org.springframework.http.ResponseEntity;
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
public class ClassroomController extends BaseController {
    private final CourseService courseService;
    private final MasterSiswaService siswaService;
    private final MasterPersonService personService;

    public ClassroomController(CourseService courseService, MasterSiswaService siswaService, MasterPersonService personService) {
        this.courseService = courseService;
        this.siswaService = siswaService;
        this.personService = personService;
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

    @GetMapping("/people-teacher")
    public ModelAndView addPeopleTeacher() {
        ModelAndView view = new ModelAndView("pages/classroom/_people-teacher-add");
        view.addObject("teacher", new CoursePersonRequest());
        addObject(view);
        return view;
    }

    @GetMapping("/people-student")
    public ModelAndView addPeopleStudent() {
        ModelAndView view = new ModelAndView("pages/classroom/_people-student-add");
        view.addObject("student", new CourseSiswaRequest());
        addObject(view);
        return view;
    }
  
    @GetMapping("/people")
    public ModelAndView people() {
        var view = new ModelAndView("pages/classroom/people");
        return view;
    }

    @GetMapping("/grades")
    public ModelAndView grade() {
        return new ModelAndView("pages/classroom/_page-grade");
    }

    @GetMapping("/add/material")
    public ModelAndView material() {
        return new ModelAndView("pages/classroom/_page-material");
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
        List<CourseSectionRes> sections = courseService.getSectionByCourseId(id);
        if (sections.isEmpty()) {
            sections = Collections.emptyList();
        }
        view.addObject("sections", sections);
        view.addObject("noUrutComparator", Comparator.comparing(CourseSectionRes::getNoUrut));
        return view;
    }

    @GetMapping("/{id}/topic/add")
    public ModelAndView addTopic(@PathVariable("id") String id) {
        ModelAndView view = new ModelAndView("pages/classroom/_topic-add");
        CourseSectionReq section = new CourseSectionReq(id, "TOPIC");
        view.addObject("section", section);
        return view;
    }

    @PostMapping("/section/save")
    public ModelAndView sectionSave(@Valid @ModelAttribute("section") CourseSectionReq request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/classroom/_topic-add");
        if (result.hasErrors()){
            addObjectTopic(view, request);
            return view;
        }

        try{
            CourseSectionRes courseSectionRes = courseService.saveSection(request).orElse(null);
            if (courseSectionRes == null){
                result.addError(new FieldError("section", "id", "Course section is null"));
                addObjectTopic(view, request);
                return view;
            }
            addObjectTopic(view, new CourseSectionReq(request.getCourseId(), "TOPIC"));
            return view;
        }catch (EduProWebException e){
            result.addError(new FieldError("section", "id", "course section is null"));
            addObjectTopic(view, request);
            return view;
        }
    }
    private void addObjectTopic(ModelAndView view, CourseSectionReq request){
        view.addObject("section", request);
    }


    private void addObject(ModelAndView view){
        view.addObject("studentList", siswaService.get());
        view.addObject("teacherList", personService.get());
    }

    @GetMapping("/data-student")
    public ResponseEntity<Response> getStudent(){
        try {
            List<SiswaResponse> result = siswaService.get();
            return getResponse(result);
        }catch (EduProWebException e){
            return getResponse(Collections.emptyList());
        }
    }

    @GetMapping("/data-teacher")
    public ResponseEntity<Response> getTeacher(){
        try {
            List<PersonResponse> result = personService.get();
            return getResponse(result);
        }catch (EduProWebException e){
            return getResponse(Collections.emptyList());
        }
    }
}
