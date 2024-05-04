package org.edupro.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
    @GetMapping("/")
    public String index(){
        return "redirect:/home";
    }

    @GetMapping("/login")
    public String login(){
        return "pages/auth/login";
    }
}
