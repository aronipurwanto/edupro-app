package org.edupro.web.user.controller;

import org.edupro.web.user.service.UserService;
import org.edupro.web.user.model.UserInfoRes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {
    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/info")
    public UserInfoRes getUserInfo() {
        return this.userService.userInfo();
    }
}
