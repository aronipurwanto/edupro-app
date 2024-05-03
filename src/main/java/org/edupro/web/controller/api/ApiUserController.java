package org.edupro.web.controller.api;

import org.edupro.web.model.response.UserInfoResponse;
import org.edupro.web.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class ApiUserController {
    private final UserService userService;

    public ApiUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/info")
    public UserInfoResponse getUserInfo() {
        return this.userService.userInfo();
    }
}
