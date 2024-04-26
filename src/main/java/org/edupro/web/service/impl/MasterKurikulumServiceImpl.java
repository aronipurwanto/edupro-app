package org.edupro.web.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.edupro.web.constant.BackEndUrl;
import org.edupro.web.model.request.KurikulumRequest;
import org.edupro.web.model.response.KurikulumResponse;
import org.edupro.web.model.response.Response;
import org.edupro.web.service.MasterKurikulumService;
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
public class MasterKurikulumServiceImpl implements MasterKurikulumService {
    private final BackEndUrl backEndUrl;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    
    @Override
    public List<KurikulumResponse> get() {
        try {
            var url = backEndUrl.kurikulumUrl();
            ResponseEntity<Response> response = restTemplate.getForEntity(url, Response.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                return (List<KurikulumResponse>) response.getBody().getData();
            }
        }catch (RestClientException e) {
            return Collections.emptyList();
        }

        return Collections.emptyList();
    }

    @Override
    public Optional<KurikulumResponse> getById(String id) {
        try {
            var url = Strings.concat(backEndUrl.kurikulumUrl(), "/"+ id);
            ResponseEntity<Response> response = restTemplate.getForEntity( url, Response.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                KurikulumResponse result = objectMapper.readValue(json, KurikulumResponse.class);

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
    public Optional<KurikulumResponse> save(KurikulumRequest request) {
        try {
            var url = backEndUrl.kurikulumUrl();
            HttpEntity<KurikulumRequest> httpEntity = new HttpEntity<>(request);
            ResponseEntity<Response> response = restTemplate.postForEntity( url, httpEntity, Response.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                KurikulumResponse result = objectMapper.readValue(json, KurikulumResponse.class);
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
    public Optional<KurikulumResponse> update(KurikulumRequest request) {
        try {
            var url = Strings.concat(backEndUrl.kurikulumUrl(),"/"+ request.getId());
            HttpEntity<KurikulumRequest> httpEntity = new HttpEntity<>(request);
            ResponseEntity<Response> response = restTemplate.exchange( url, HttpMethod.PUT, httpEntity, Response.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                KurikulumResponse result = objectMapper.readValue(json, KurikulumResponse.class);
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
    public Optional<KurikulumResponse> delete(KurikulumRequest request) {
        try {
            var url = Strings.concat(backEndUrl.kurikulumUrl(),"/"+ request.getId());
            ResponseEntity<Response> response = restTemplate.exchange( url, HttpMethod.DELETE, null, Response.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                KurikulumResponse result = objectMapper.readValue(json, KurikulumResponse.class);
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
