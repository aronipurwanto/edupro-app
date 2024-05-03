package org.edupro.web.service.impl;

import org.edupro.web.model.response.UserInfoResponse;
import org.edupro.web.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserInfoResponse userInfo() {
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

        UserInfoResponse response = new UserInfoResponse();
        response.setUserId(userId);
        response.setUsername(user.getName());
        response.setEmail(user.getEmail());
        response.setToken(token.getTokenValue());

        return response;
    }
}
