package org.edupro.web.base.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import org.edupro.web.base.model.Response;
import org.edupro.web.base.model.ResponseError;
import org.edupro.web.constant.CommonConstant;
import org.edupro.web.exception.EduProWebException;
import org.edupro.web.util.CommonUtil;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

@Service
public class BaseService<T,R> {

    private final String backEndUrl;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    // Constructor
    public BaseService(String backEndUrl, RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.backEndUrl = backEndUrl;
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public String getToken() {
        final DefaultOidcUser user = (DefaultOidcUser) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        OidcIdToken token = user.getIdToken();
        if (token == null) {
            return "";
        }
        return token.getTokenValue();
    }

    public HttpHeaders getHeader() {
        String token = getToken();
        if (token == null || token.isEmpty()) {
            List<FieldError> errors = List.of(new FieldError("token", "token", CommonConstant.Error.ERR_TOKEN_EMPTY));
            throw new EduProWebException(CommonConstant.Error.ERR_API, errors);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", "Bearer " + token);

        return headers;
    }

    public HttpEntity<String> getHttpEntity() {
        return new HttpEntity<>(null, getHeader());
    }

    private String buildUrl(String urlSuffix) {
        return backEndUrl + urlSuffix;
    }

    private Optional<T> handleResponseForObject(ResponseEntity<Response> response, Class<T> clazz) throws IOException {
        if (response.getStatusCode() == HttpStatus.OK) {
            byte[] json = objectMapper.writeValueAsBytes(response.getBody().getData());
            return Optional.of(objectMapper.readValue(json, clazz));
        }
        return Optional.empty();
    }

    private List<T> handleResponseForList(ResponseEntity<Response> response, Class<T> clazz) throws IOException {
        if (response.getStatusCode() == HttpStatus.OK) {
            String json = objectMapper.writeValueAsString(response.getBody().getData());
            return CommonUtil.jsonArrayToList(json, clazz);
        }
        return Collections.emptyList();
    }

    private List<T> getListData(String urlSuffix, Class<T> clazz) {
        try {
            String url = buildUrl(urlSuffix);
            ResponseEntity<Response> response = restTemplate.exchange(url, HttpMethod.GET, getHttpEntity(), Response.class);
            return handleResponseForList(response, clazz);
        } catch (Exception e) {
            // Consider logging the error for debugging purposes
            throw new EduProWebException(CommonConstant.Error.ERR_API, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<T> get(Class<T> clazz) {
        return getListData("", clazz);
    }

    public List<T> getByGroup(String group, Class<T> clazz) {
        return getListData("/group/" + group, clazz);
    }

    public List<T> getByCustomUrl(String url, Class<T> clazz) {
        return getListData(url, clazz);
    }

    public List<T> getGroup(Class<T> clazz) {
        return getListData("/group", clazz);
    }

    public Optional<T> getById(String id, Class<T> clazz) {
        try {
            String url = buildUrl("/" + id);
            ResponseEntity<Response> response = restTemplate.exchange(url, HttpMethod.GET, getHttpEntity(), Response.class);
            return handleResponseForObject(response, clazz);
        } catch (Exception e) {
            // Consider logging the error for debugging purposes
            throw new EduProWebException(CommonConstant.Error.ERR_API, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Optional<T> save(R request, Class<T> clazz) {
        try {
            String url = buildUrl("");
            HttpEntity<R> entity = new HttpEntity<>(request, getHeader());
            ResponseEntity<Response> response = restTemplate.exchange(url, HttpMethod.POST, entity, Response.class);
            return handleResponseForObject(response, clazz);
        } catch (Exception e) {
            // Consider logging the error for debugging purposes
            throw new EduProWebException(CommonConstant.Error.ERR_API, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Optional<T> update(R request, String id, Class<T> clazz) {
        try {
            String url = buildUrl("/" + id);
            HttpEntity<R> entity = new HttpEntity<>(request, getHeader());
            ResponseEntity<Response> response = restTemplate.exchange(url, HttpMethod.PUT, entity, Response.class);
            return handleResponseForObject(response, clazz);
        } catch (Exception e) {
            // Consider logging the error for debugging purposes
            throw new EduProWebException(CommonConstant.Error.ERR_API, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Optional<T> delete(String id, Class<T> clazz) {
        try {
            String url = buildUrl("/" + id);
            ResponseEntity<Response> response = restTemplate.exchange(url, HttpMethod.DELETE, getHttpEntity(), Response.class);
            return handleResponseForObject(response, clazz);
        } catch (Exception e) {
            // Consider logging the error for debugging purposes
            throw new EduProWebException(CommonConstant.Error.ERR_API, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    protected List<FieldError> readError(IOException e) {
        return List.of(new FieldError("error", "error", e.getMessage()));
    }
    protected List<FieldError> readError(JsonProcessingException e) {
        return List.of(new FieldError("error", "error", e.getMessage()));
    }

    protected List<FieldError> readError(RestClientException e){
        String message = e.getMessage();
        if(message.contains("400")) {
            return readError400(message);
        }

        if(message.contains("401")) {
            return readError401(message);
        }

        if(message.contains("500")) {
            return readError500(message);
        }
        return Collections.emptyList();
    }

    protected List<FieldError> readError500(String message){
        message = message.replace("500 :","");
        message = message.substring(2, message.length()-1);

        return Arrays.asList(
                new FieldError("","id",message)
        );
    }

    protected List<FieldError> readError401(String message){
        return Arrays.asList(
                new FieldError("","id","Unauthorized token")
        );
    }

    protected List<FieldError> readError400(String message){
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
