package org.edupro.web.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.edupro.web.constant.BackEndUrl;
import org.edupro.web.model.request.SesiRequest;
import org.edupro.web.model.response.SesiResponse;
import org.edupro.web.model.response.Response;
import org.edupro.web.service.MasterSesiService;
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
public class MasterSesiServiceImpl implements MasterSesiService {
    private final BackEndUrl backEndUrl;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

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
    public Optional<SesiResponse> getById(String id) {
        try {
            var url = Strings.concat(backEndUrl.sesiUrl(), "/" + id);
            ResponseEntity<Response> response = restTemplate.getForEntity(url, Response.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                SesiResponse result = objectMapper.readValue(json, SesiResponse.class);

                return Optional.of(result);
            }
        }catch (RestClientException e){
            return Optional.empty();
        }catch (IOException e) {
            throw  new RuntimeException(e);
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
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                SesiResponse result = objectMapper.readValue(json, SesiResponse.class);

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
    public Optional<SesiResponse> update(SesiRequest request, String id) {
        try{
            var url = Strings.concat(backEndUrl.sesiUrl(),"/" + id);
            HttpEntity<SesiRequest> httpEntity = new HttpEntity<>(request);
            ResponseEntity<Response> response = restTemplate.exchange( url, HttpMethod.PUT, httpEntity, Response.class);
            if(response.getStatusCode() == HttpStatus.OK) {
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                SesiResponse result = objectMapper.readValue(json, SesiResponse.class);

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
    public Optional<SesiResponse> delete(String id) {
        try{
            var url = Strings.concat(backEndUrl.sesiUrl(),"/" + id);
            ResponseEntity<Response> response = restTemplate.exchange( url, HttpMethod.DELETE, null, Response.class);
            if(response.getStatusCode() == HttpStatus.OK) {
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                SesiResponse result = objectMapper.readValue(json, SesiResponse.class);

                return Optional.of(result);
            }
        }catch (RestClientException e){
            return Optional.empty();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
}
