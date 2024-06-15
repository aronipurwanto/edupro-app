package org.edupro.web.user.service;

import org.edupro.web.user.model.UserInfoRes;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserInfoRes userInfo() {
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

        UserInfoRes response = new UserInfoRes();
        response.setUserId(userId);
        response.setUsername(user.getName());
        response.setEmail(user.getEmail());
        response.setToken(token.getTokenValue());

        return response;
    }
}
