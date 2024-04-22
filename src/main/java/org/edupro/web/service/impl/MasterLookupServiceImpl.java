package org.edupro.web.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.edupro.web.constant.BackEndUrl;
import org.edupro.web.model.request.LookupRequest;
import org.edupro.web.model.response.CommonResponse;
import org.edupro.web.model.response.LookupResponse;
import org.edupro.web.model.response.Response;
import org.edupro.web.service.MasterLookupService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class MasterLookupServiceImpl implements MasterLookupService {
    private final BackEndUrl backEndUrl;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public List<LookupResponse> get() {
        try {
            var url = backEndUrl.lookupUrl();
            ResponseEntity<Response> response = restTemplate.getForEntity(url, Response.class);
            if(response.getStatusCode() == HttpStatus.OK) {
                return (List<LookupResponse>) response.getBody().getData();
            }
        }catch (RestClientException e){
            return Collections.emptyList();
        }

        return Collections.emptyList();
    }

    @Override
    public List<CommonResponse> getGroup() {
        try {
            var url = Strings.concat(backEndUrl.lookupUrl(),"/group");
            ResponseEntity<Response> response = restTemplate.getForEntity(url, Response.class);
            if(response.getStatusCode() == HttpStatus.OK) {
                return (List<CommonResponse>) response.getBody().getData();
            }
        }catch (RestClientException e){
            return Collections.emptyList();
        }

        return Collections.emptyList();
    }

    @Override
    public Optional<LookupResponse> getById(Integer id) {
        try {
            var url = Strings.concat(backEndUrl.lookupUrl(), "/"+ id);
            ResponseEntity<Response> response = restTemplate.getForEntity( url, Response.class);
            if(response.getStatusCode() == HttpStatus.OK) {
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                LookupResponse result = objectMapper.readValue(json, LookupResponse.class);

                return Optional.of(result);
            }
        }catch (RestClientException e){
            return Optional.empty();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<LookupResponse> save(LookupRequest request) {
        try{
            var url = backEndUrl.lookupUrl();
            HttpEntity<LookupRequest> httpEntity = new HttpEntity<>(request);
            ResponseEntity<Response> response = restTemplate.postForEntity( url, httpEntity, Response.class);
            if(response.getStatusCode() == HttpStatus.OK) {
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                LookupResponse result = objectMapper.readValue(json, LookupResponse.class);
                return Optional.of(result);
            }
        }catch (RestClientException e){
            return Optional.empty();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<LookupResponse> update(LookupRequest request) {
        try{
            var url = Strings.concat(backEndUrl.lookupUrl(),"/"+ request.getId());
            HttpEntity<LookupRequest> httpEntity = new HttpEntity<>(request);
            ResponseEntity<Response> response = restTemplate.exchange( url, HttpMethod.PUT, httpEntity, Response.class);
            if(response.getStatusCode() == HttpStatus.OK) {
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                LookupResponse result = objectMapper.readValue(json, LookupResponse.class);
                return Optional.of(result);
            }
        }catch (RestClientException e){
            return Optional.empty();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<LookupResponse> delete(Integer id) {
        try{
            var url = Strings.concat(backEndUrl.lookupUrl(),"/"+ id);
            ResponseEntity<Response> response = restTemplate.exchange( url, HttpMethod.DELETE, null, Response.class);
            if(response.getStatusCode() == HttpStatus.OK) {
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                LookupResponse result = objectMapper.readValue(json, LookupResponse.class);
                return Optional.of(result);
            }
        }catch (RestClientException e){
            return Optional.empty();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
}
