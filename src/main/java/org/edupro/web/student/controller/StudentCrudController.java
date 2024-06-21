package org.edupro.web.student.controller;

import com.google.gson.Gson;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.edupro.web.base.controller.BaseController;
import org.edupro.web.exception.EduProWebException;
import org.edupro.web.student.model.StudentReq;
import org.edupro.web.base.model.Response;
import org.edupro.web.student.model.StudentRes;
import org.edupro.web.lookup.service.LookupService;
import org.edupro.web.student.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class StudentCrudController {
}
