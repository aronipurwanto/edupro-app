package org.edupro.web.classroom.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.web.course.model.CourseSectionReq;
import org.edupro.web.course.model.CourseSectionRequest;
import org.edupro.web.course.model.CourseSectionRes;
import org.edupro.web.classroom.service.CourseSectionService;
import org.edupro.web.exception.EduProWebException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/classroom")
@RequiredArgsConstructor
public class SectionController {
    private final CourseSectionService sectionService;

    @GetMapping("/{id}/material/add")
    public ModelAndView material(@PathVariable("id") String id) {
        ModelAndView view =  new ModelAndView("pages/classroom/_page-material");
        CourseSectionReq section = new CourseSectionReq(id, "MATERIAL");
        view.addObject("sectionList", new CourseSectionRequest());
        view.addObject("section", section);
        addObjectSection(view);
        return view;
    }

    @GetMapping("/{id}/topic/add")
    public ModelAndView addTopic(@PathVariable("id") String id) {
        ModelAndView view = new ModelAndView("pages/classroom/_topic-add");
        CourseSectionReq section = new CourseSectionReq(id, "TOPIC");
        view.addObject("section", section);
        return view;
    }

    @GetMapping("/{id}/quiz/add")
    public ModelAndView addQuiz(@PathVariable("id") String id) {
        ModelAndView view = new ModelAndView("pages/classroom/_topic-quiz");
        CourseSectionReq section = new CourseSectionReq(id, "QUIZ");
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
            CourseSectionRes courseSectionRes = sectionService.saveSection(request).orElse(null);
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

    public void addObjectSection(ModelAndView view){
        List<CourseSectionRes> sectionRes = this.sectionService.getSectionByCourseId("");
        view.addObject("sectionData", sectionRes );
    }

}
