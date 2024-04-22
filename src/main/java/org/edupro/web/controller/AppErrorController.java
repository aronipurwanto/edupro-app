package org.edupro.web.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppErrorController implements ErrorController {
    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletRequest request){
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if(status == null){
            return new ModelAndView("pages/error/index");
        }

        int statusCode = Integer.valueOf(status.toString());

        if(statusCode >= 400 && statusCode < 500){
            ModelAndView view = new ModelAndView("pages/error/error-400");
            view.addObject("code", statusCode);
            view.addObject("message",HttpStatus.valueOf(statusCode).getReasonPhrase());
            return view;
        }

        if(statusCode >= 500){
            ModelAndView view = new ModelAndView("pages/error/error-500");
            view.addObject("code", statusCode);
            view.addObject("message",HttpStatus.valueOf(statusCode).getReasonPhrase());
            return view;
        }

        return new ModelAndView("pages/error/index");
    }
}
