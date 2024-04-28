package org.edupro.web.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.edupro.web.constant.BackEndUrl;
import org.edupro.web.model.request.LembagaRequest;
import org.edupro.web.model.response.LembagaResponse;
import org.edupro.web.model.response.Response;
import org.edupro.web.service.MasterLembagaService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MasterLembagaServiceImpl implements MasterLembagaService {
    private final BackEndUrl backEndUrl;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    @Override
    public List<LembagaResponse> get() {
        try {
            var url = backEndUrl.lembagaUrl();
            ResponseEntity<Response> response = restTemplate.getForEntity(url, Response.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                return (List<LembagaResponse>) response.getBody().getData();
            }
        }catch (RestClientException e) {
            return Collections.emptyList();
        }

        return Collections.emptyList();
    }

    @Override
    public Optional<LembagaResponse> getById(String id) {
        try {
            var url = Strings.concat(backEndUrl.lembagaUrl(), "/"+ id);
            ResponseEntity<Response> response = restTemplate.getForEntity( url, Response.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                LembagaResponse result = objectMapper.readValue(json, LembagaResponse.class);

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
    public Optional<LembagaResponse> save(LembagaRequest request) {
        try {
            var url = backEndUrl.lembagaUrl();
            HttpEntity<LembagaRequest> httpEntity = new HttpEntity<>(request);
            ResponseEntity<Response> response = restTemplate.postForEntity( url, httpEntity, Response.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                LembagaResponse result = objectMapper.readValue(json, LembagaResponse.class);
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
    public Optional<LembagaResponse> update(LembagaRequest request, String id) {
        try {
            var url = Strings.concat(backEndUrl.lembagaUrl(),"/"+ id);
            HttpEntity<LembagaRequest> httpEntity = new HttpEntity<>(request);
            ResponseEntity<Response> response = restTemplate.exchange( url, HttpMethod.PUT, httpEntity, Response.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                LembagaResponse result = objectMapper.readValue(json, LembagaResponse.class);
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
    public Optional<LembagaResponse> delete(String id) {
        try {
            var url = Strings.concat(backEndUrl.lembagaUrl(),"/"+ id);
            ResponseEntity<Response> response = restTemplate.exchange( url, HttpMethod.DELETE, null, Response.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                LembagaResponse result = objectMapper.readValue(json, LembagaResponse.class);
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
