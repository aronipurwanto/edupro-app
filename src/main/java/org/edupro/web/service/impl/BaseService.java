package org.edupro.web.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import jakarta.validation.constraints.Null;
import org.edupro.web.constant.CommonConstant;
import org.edupro.web.exception.EduProWebException;
import org.edupro.web.model.response.ResponseError;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class BaseService {

    public String getToken(){
        final DefaultOidcUser user = (DefaultOidcUser) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        OidcIdToken token = user.getIdToken();
        if (token == null) {
            return "";
        }
        return token.getTokenValue();
    }

    public HttpHeaders getHeader(){
        if(getToken() == null || getToken().isEmpty()){
            List<FieldError> errors = List.of(new FieldError("token", "token", CommonConstant.Error.ERR_TOKEN_EMPTY));
            throw new EduProWebException(CommonConstant.Error.ERR_API, errors);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", "Bearer " + getToken());

        return headers;
    }

    public HttpEntity<String> getHttpEntity(){
        return new HttpEntity<>(null, getHeader());
    }

    protected List<FieldError> readError(RestClientException e){
        String message = e.getMessage();
        if(!message.contains("400")){
            return Collections.emptyList();
        }

        message = message.replace("400 :","");
        message = message.substring(2, message.length()-1);

        ResponseError errorApi;
        try {
            JsonObject jsonObject = JsonParser.parseString(message).getAsJsonObject();
            errorApi = new Gson().fromJson(jsonObject, ResponseError.class);
        }catch (JsonSyntaxException ex){
            return Collections.emptyList();
        }

        if(errorApi.getErrors() == null || errorApi.getErrors().isEmpty()) {
            return Collections.emptyList();
        }

        List<FieldError> result  = new ArrayList<>();
        for(Map<String,String> error: errorApi.getErrors()){
            for(Map.Entry<String,String> entry: error.entrySet()){
                FieldError fieldError = new FieldError("", entry.getKey(),entry.getValue());
                result.add(fieldError);
            }
        }

        return result;
    }
}
