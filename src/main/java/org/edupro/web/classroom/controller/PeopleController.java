package org.edupro.web.classroom.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.web.base.controller.BaseController;
import org.edupro.web.base.model.Response;
import org.edupro.web.classroom.service.CoursePeopleService;
import org.edupro.web.classroom.service.CourseStudentService;
import org.edupro.web.course.model.CoursePeopleRes;
import org.edupro.web.course.model.CoursePersonReq;
import org.edupro.web.course.model.CourseStudentReq;
import org.edupro.web.course.model.CourseStudentRes;
import org.edupro.web.exception.EduProWebException;
import org.edupro.web.person.model.PersonRes;
import org.edupro.web.student.model.StudentRes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/classroom")
@RequiredArgsConstructor
public class PeopleController extends BaseController {
    private final CoursePeopleService peopleService;
    private final CourseStudentService studentService;

    @GetMapping("/{id}/people-teacher")
    public ModelAndView addPeopleTeacher(@PathVariable("id") String id) {
        ModelAndView view = new ModelAndView("pages/classroom/_people-teacher-add");
        view.addObject("teacher", new CoursePersonReq(id));
        addObjectPeople(view);
        return view;
    }

    @PostMapping("/teacher/save")
    public ModelAndView savePeople(@ModelAttribute("teacher") @Valid CoursePersonReq request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/classroom/_people-teacher-add");
        view.addObject("teacher", request);
        if (result.hasErrors()){
            return view;
        }
        return new ModelAndView("redirect:/classrom/"+request.getCourseId()+"/people");
    }

    @GetMapping("/{id}/people-student")
    public ModelAndView addPeopleStudent(@PathVariable("id") String id) {
        ModelAndView view = new ModelAndView("pages/classroom/_people-student-add");
        view.addObject("student", new CourseStudentReq(id));
        addObjectPeople(view);
        return view;
    }

    @GetMapping("/{id}/people")
    public ModelAndView people(@PathVariable("id") String id) {
        ModelAndView view = new ModelAndView("pages/classroom/people");
        CoursePeopleRes people = peopleService.getPeople(id).orElse(null);
        view.addObject("people", people);
        return view;
    }

    @PostMapping("/people-student/save")
    public ModelAndView studentSave(@Valid @ModelAttribute("student") CourseStudentReq request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/classroom/_people-student-add");
        if (result.hasErrors()){
            addObjectStudent(view, request);
            return view;
        }

        try {
            CourseStudentRes courseStudentRes = studentService.saveSiswa(request).orElse(null);
            if (courseStudentRes == null){
                result.addError(new FieldError("student", "id", "Course siswa is null"));
                addObjectStudent(view, request);
                return view;
            }
            addObjectStudent(view, new CourseStudentReq(request.getCourseId(), request.getSiswaId()));
            return view;
        }catch (EduProWebException e){
            result.addError(new FieldError("student", "id", "Course siswa is null"));
            addObjectStudent(view, request);
            return view;
        }
    }

    @GetMapping("/data-student")
    public ResponseEntity<Response> getStudent(){
        try {
            List<StudentRes> result = Collections.emptyList(); //peopleService.getPeople();
            return getResponse(result);
        }catch (EduProWebException e){
            return getResponse(Collections.emptyList());
        }
    }

    @GetMapping("/data-teacher")
    public ResponseEntity<Response> getTeacher(){
        try {
            List<PersonRes> result = Collections.emptyList(); //personService.get();
            return getResponse(result);
        }catch (EduProWebException e){
            return getResponse(Collections.emptyList());
        }
    }

    private void addObjectStudent(ModelAndView view, CourseStudentReq request){
        view.addObject("student", request);
    }

    private void addObjectPeople(ModelAndView view){
        view.addObject("studentList", Collections.emptyList());
        view.addObject("teacherList", Collections.emptyList());
    }
}
