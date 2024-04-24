package org.edupro.web.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.edupro.web.constant.BackEndUrl;
import org.edupro.web.model.request.GedungRequest;
import org.edupro.web.model.response.GedungResponse;
import org.edupro.web.model.response.Response;
import org.edupro.web.service.MasterGedungService;
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
public class MasterGedungServiceImpl implements MasterGedungService {
    private final BackEndUrl backEndUrl;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    @Override
    public List<GedungResponse> get() {
        try {
            var url = backEndUrl.gedungUrl();
            ResponseEntity<Response> response = restTemplate.getForEntity(url, Response.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                return (List<GedungResponse>) response.getBody().getData();
            }
        }catch (RestClientException e) {
            return Collections.emptyList();
        }

        return Collections.emptyList();
    }

    @Override
    public Optional<GedungResponse> getById(String id) {
        try {
            var url = Strings.concat(backEndUrl.gedungUrl(), "/"+ id);
            ResponseEntity<Response> response = restTemplate.getForEntity( url, Response.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                GedungResponse result = objectMapper.readValue(json, GedungResponse.class);

                return Optional.of(result);
            }
        }catch (RestClientException e){
            return Optional.empty();
        }catch (IOException e){
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    @Override
    public Optional<GedungResponse> save(GedungRequest request) {
        try {
            var url = backEndUrl.gedungUrl();
            HttpEntity<GedungRequest> httpEntity = new HttpEntity<>(request);
            ResponseEntity<Response> response = restTemplate.postForEntity( url, httpEntity, Response.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                GedungResponse result = objectMapper.readValue(json, GedungResponse.class);
                return Optional.of(result);
            }
        }catch (RestClientException e){
            return Optional.empty();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }


    @Override
    public Optional<GedungResponse> update(GedungRequest request) {
        try {
            var url = Strings.concat(backEndUrl.gedungUrl(),"/"+ request.getId());
            HttpEntity<GedungRequest> httpEntity = new HttpEntity<>(request);
            ResponseEntity<Response> response = restTemplate.exchange( url, HttpMethod.PUT, httpEntity, Response.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                GedungResponse result = objectMapper.readValue(json, GedungResponse.class);
                return Optional.of(result);
            }
        }catch (RestClientException e){
            return Optional.empty();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<GedungResponse> delete(GedungRequest request) {
        try {
            var url = Strings.concat(backEndUrl.gedungUrl(),"/"+ request.getId());
            ResponseEntity<Response> response = restTemplate.exchange( url, HttpMethod.DELETE, null, Response.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                GedungResponse result = objectMapper.readValue(json, GedungResponse.class);
                return Optional.of(result);
            }
        }catch (RestClientException e){
            return Optional.empty();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
}
