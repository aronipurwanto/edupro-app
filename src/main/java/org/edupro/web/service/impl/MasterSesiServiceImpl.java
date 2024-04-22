package org.edupro.web.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.edupro.web.constant.BackEndUrl;
import org.edupro.web.model.request.KelasRequest;
import org.edupro.web.model.request.SesiRequest;
import org.edupro.web.model.request.SesiRequest;
import org.edupro.web.model.response.KelasResponse;
import org.edupro.web.model.response.SesiResponse;
import org.edupro.web.model.response.Response;
import org.edupro.web.model.response.SesiResponse;
import org.edupro.web.service.MasterSesiService;
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
public class MasterSesiServiceImpl implements MasterSesiService {
    private final BackEndUrl backEndUrl;
    private final RestTemplate restTemplate;

    @Override
    public List<SesiResponse> get() {
        try {
            var url = backEndUrl.sesiUrl();
            ResponseEntity<Response> response = restTemplate.getForEntity(url, Response.class);
            if(response.getStatusCode() == HttpStatus.OK) {
                return (List<SesiResponse>) response.getBody().getData();
            }
        }catch (RestClientException e){
            return Collections.emptyList();
        }

        return Collections.emptyList();
    }

    @Override
    public Optional<SesiResponse> getById(Integer id) {
        try {
            var url = Strings.concat(backEndUrl.sesiUrl(), "/"+ id);
            ResponseEntity<Response> response = restTemplate.getForEntity( url, Response.class);
            if(response.getStatusCode() == HttpStatus.OK) {
                var result = (SesiResponse) response.getBody().getData();
                return Optional.of(result);
            }
        }catch (RestClientException e){
            return Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public Optional<SesiResponse> save(SesiRequest request) {
        try{
            var url = backEndUrl.sesiUrl();
            HttpEntity<SesiRequest> httpEntity = new HttpEntity<>(request);
            ResponseEntity<Response> response = restTemplate.postForEntity( url, httpEntity, Response.class);
            if(response.getStatusCode() == HttpStatus.OK) {
                var result = (SesiResponse) response.getBody().getData();
                return Optional.of(result);
            }
        }catch (RestClientException e){
            return Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public Optional<SesiResponse> update(SesiRequest request, Integer id) {
        try{
            var url = Strings.concat(backEndUrl.sesiUrl(),"/"+ id);
            HttpEntity<SesiRequest> httpEntity = new HttpEntity<>(request);
            ResponseEntity<Response> response = restTemplate.exchange( url, HttpMethod.PUT, httpEntity, Response.class);
            if(response.getStatusCode() == HttpStatus.OK) {
                var result = (SesiResponse) response.getBody().getData();
                return Optional.of(result);
            }
        }catch (RestClientException e){
            return Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public Optional<SesiResponse> delete(Integer id) {
        try{
            var url = Strings.concat(backEndUrl.sesiUrl(),"/"+ id);
            ResponseEntity<Response> response = restTemplate.exchange( url, HttpMethod.DELETE, null, Response.class);
            if(response.getStatusCode() == HttpStatus.OK) {
                var result = (SesiResponse) response.getBody().getData();
                return Optional.of(result);
            }
        }catch (RestClientException e){
            return Optional.empty();
        }
        return Optional.empty();
    }
}
