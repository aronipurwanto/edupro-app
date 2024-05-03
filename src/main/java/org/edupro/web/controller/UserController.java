package org.edupro.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("users")
public class UserController {
    @GetMapping
    public ModelAndView getProfile() {
        ModelAndView view = new ModelAndView("pages/users/index");

        return view;
    }
}
