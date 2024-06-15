package org.edupro.web.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoRes {
    private String userId;
    private String username;
    private String email;
    private String phone;
    private String token;
}
