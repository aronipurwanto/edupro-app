package org.edupro.web.classroom.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.web.academic.service.AcademicService;
import org.edupro.web.academic.model.AcademicYearRes;
import org.edupro.web.academic.model.AcademicSessionRes;
import org.edupro.web.base.model.Response;
import org.edupro.web.building.service.RoomService;
import org.edupro.web.building.model.RoomRes;
import org.edupro.web.classroom.model.ClassReq;
import org.edupro.web.classroom.model.ClassRes;
import org.edupro.web.classroom.service.ClassService;
import org.edupro.web.base.controller.BaseController;
import org.edupro.web.exception.EduProWebException;
import org.edupro.web.institution.model.InstitutionRes;
import org.edupro.web.institution.service.InstitutionService;
import org.edupro.web.level.model.LevelRes;
import org.edupro.web.level.service.LevelService;
import org.edupro.web.person.service.PersonService;
import org.edupro.web.person.model.PersonRes;
import org.edupro.web.academic.service.AcademicSessionServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/master/kelas")
public class ClassController extends BaseController<ClassRes> {
    private final ClassService service;
    private final RoomService ruanganService;
    private final LevelService levelService;
    private final InstitutionService lembagaService;
    private final PersonService personService;
    private final AcademicService tahunAjaranService;
    private final AcademicSessionServiceImpl sesiService;

    @GetMapping
    public ModelAndView index(){
        var view = new ModelAndView("pages/master/kelas/index");
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("pages/master/kelas/add");

        view.addObject("kelas", new ClassReq());
        addObject(view);
        return view;
    }

    public void addObject(ModelAndView view){
        List<LevelRes> level = levelService.get();
        view.addObject("level", level);

        List<RoomRes> ruangan = ruanganService.get();
        view.addObject("ruangan", ruangan);

        List<InstitutionRes> lembaga = lembagaService.get();
        view.addObject("lembaga", lembaga);

        List<PersonRes> person = personService.get();
        view.addObject("person", person);

        List<AcademicSessionRes> sesi = sesiService.get();
        view.addObject("sesi", sesi);

        List<AcademicYearRes> tahunAjaran = tahunAjaranService.get();
        view.addObject("tahunAjaran", tahunAjaran);

    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute("kelas") @Valid ClassReq classReq, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/kelas/add");
        view.addObject("kelas", classReq);

        if(result.hasErrors()){
            addObject(view);
            return view;
        }

        try {
            service.save(classReq);
            return new ModelAndView("redirect:/master/kelas");
        }catch (EduProWebException e){
            addError("kelas", result,(List<FieldError>)e.getErrors());
            return view;
        }
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/master/kelas/edit");
        return getModelAndView(id, view);
    }

    private ModelAndView getModelAndView(String id, ModelAndView view) {
        ClassRes result;
        try {
            result = this.service.getById(id).orElse(null);
        }catch (EduProWebException e){
            return new ModelAndView("pages/error/modal-500");
        }

        if (result == null){
            return new ModelAndView("pages/error/modal-not-found");
        }

        view.addObject("kelas", result);
        addObject(view);

        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute("kelas") @Valid ClassReq request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/kelas/edit");
        view.addObject("kelas", request);
        if(result.hasErrors()){
            addObject(view);
            return view;
        }

        try {
            service.update(request, request.getId()).orElse(null);
            return new ModelAndView("redirect:/master/kelas");
        }catch (EduProWebException e){
            addError("kelas", result,(List<FieldError>)e.getErrors());
            return view;
        }
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id){
        ModelAndView view = new ModelAndView("pages/master/kelas/delete");
        return getModelAndView(id, view);
    }

    @PostMapping("/remove")
    public ModelAndView remove(@ModelAttribute("kelas") @Valid ClassReq request, BindingResult result){
        ModelAndView view = new ModelAndView("pages/master/kelas/delete");
        view.addObject("kelas", request);
        if(result.hasErrors()){
            addObject(view);
            return view;
        }

        try {
            service.delete(request.getId()).orElse(null);
            return new ModelAndView("redirect:/master/kelas");
        }catch (EduProWebException e){
            addError("kelas", result,(List<FieldError>)e.getErrors());
            return view;
        }
    }

    @GetMapping("/data")
    public ResponseEntity<Response> getData(){
        try {
            List<ClassRes> result = service.get();
            return getResponse(result);
        }catch (EduProWebException e){
            return getResponse(Collections.emptyList());
        }
    }
}
