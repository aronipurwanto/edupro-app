package org.edupro.web.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class AuthController {
    @GetMapping("/login")
    public String login(){
        return "pages/auth/login";
    }

    @GetMapping(path = "/users")
    public String getUserInfo() {

        final DefaultOidcUser user = (DefaultOidcUser) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        String dob = "";
        String userId = "";

        OidcIdToken token = user.getIdToken();

        Map<String, Object> customClaims = token.getClaims();

        if (customClaims.containsKey("user_id")) {
            userId = String.valueOf(customClaims.get("user_id"));
        }

        if (customClaims.containsKey("DOB")) {
            dob = String.valueOf(customClaims.get("DOB"));
        }

        ModelAndView view = new ModelAndView("pages/auth/users");
        view.addObject("username", user.getName());
        view.addObject("userID", userId);
        view.addObject("dob", dob);
        return "userInfo";
    }
}
