package org.edupro.web.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.edupro.web.constant.BackEndUrl;
import org.edupro.web.model.request.LevelRequest;
import org.edupro.web.model.request.LevelRequest;
import org.edupro.web.model.response.LevelResponse;
import org.edupro.web.model.response.LevelResponse;
import org.edupro.web.model.response.Response;
import org.edupro.web.service.MasterLevelService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MasterLevelServiceImpl implements MasterLevelService {
    private final BackEndUrl backEndUrl;
    private final RestTemplate restTemplate;

    @Override
    public List<LevelResponse> get() {
        try {
            var url = backEndUrl.levelUrl();
            ResponseEntity<Response> response = restTemplate.getForEntity(url, Response.class);
            if(response.getStatusCode() == HttpStatus.OK) {
                return (List<LevelResponse>) response.getBody().getData();
            }
        }catch (RestClientException e){
            return Collections.emptyList();
        }

        return Collections.emptyList();
    }

    @Override
    public Optional<LevelResponse> getById(Integer id) {
        try {
            var url = Strings.concat(backEndUrl.levelUrl(), "/"+ id);
            ResponseEntity<Response> response = restTemplate.getForEntity( url, Response.class);
            if(response.getStatusCode() == HttpStatus.OK) {
                var result = (LevelResponse) response.getBody().getData();
                return Optional.of(result);
            }
        }catch (RestClientException e){
            return Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public Optional<LevelResponse> save(LevelRequest request) {
        try{
            var url = backEndUrl.levelUrl();
            HttpEntity<LevelRequest> httpEntity = new HttpEntity<>(request);
            ResponseEntity<Response> response = restTemplate.postForEntity( url, httpEntity, Response.class);
            if(response.getStatusCode() == HttpStatus.OK) {
                var result = (LevelResponse) response.getBody().getData();
                return Optional.of(result);
            }
        }catch (RestClientException e){
            return Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public Optional<LevelResponse> update(LevelRequest request, Integer id) {
        try{
            var url = Strings.concat(backEndUrl.levelUrl(),"/"+ id);
            HttpEntity<LevelRequest> httpEntity = new HttpEntity<>(request);
            ResponseEntity<Response> response = restTemplate.exchange( url, HttpMethod.PUT, httpEntity, Response.class);
            if(response.getStatusCode() == HttpStatus.OK) {
                var result = (LevelResponse) response.getBody().getData();
                return Optional.of(result);
            }
        }catch (RestClientException e){
            return Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public Optional<LevelResponse> delete(Integer id) {
        try{
            var url = Strings.concat(backEndUrl.levelUrl(),"/"+ id);
            ResponseEntity<Response> response = restTemplate.exchange( url, HttpMethod.DELETE, null, Response.class);
            if(response.getStatusCode() == HttpStatus.OK) {
                var result = (LevelResponse) response.getBody().getData();
                return Optional.of(result);
            }
        }catch (RestClientException e){
            return Optional.empty();
        }
        return Optional.empty();
    }
}