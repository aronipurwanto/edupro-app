package org.edupro.web.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.edupro.web.constant.BackEndUrl;
import org.edupro.web.model.request.MapelRequest;
import org.edupro.web.model.response.MapelResponse;
import org.edupro.web.model.response.Response;
import org.edupro.web.service.MasterMapelService;
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
public class MasterMapelServiceImpl implements MasterMapelService {
    private final BackEndUrl backEndUrl;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public List<MapelResponse> get() {
        try {
            var url = backEndUrl.mapelUrl();
            ResponseEntity<Response> response = restTemplate.getForEntity(url, Response.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                return (List<MapelResponse>) response.getBody().getData();
            }
        }catch (RestClientException e){
            return Collections.emptyList();
        }

        return List.of();
    }

    @Override
    public Optional<MapelResponse> getById(String id) {
        try {
            var url = Strings.concat(backEndUrl.mapelUrl(), "/"+ id);
            ResponseEntity<Response> response = restTemplate.getForEntity(url, Response.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                MapelResponse result = objectMapper.readValue(json, MapelResponse.class);

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
    public Optional<MapelResponse> save(MapelRequest request) {
        try {
            var url = backEndUrl.mapelUrl();
            HttpEntity<MapelRequest> httpEntity = new HttpEntity<>(request);
            ResponseEntity<Response> response = restTemplate.postForEntity( url, httpEntity, Response.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                MapelResponse result = objectMapper.readValue(json, MapelResponse.class);
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
    public Optional<MapelResponse> update(MapelRequest request, String id) {
        try {
            var url = Strings.concat(backEndUrl.mapelUrl(), "/"+ id);
            HttpEntity<MapelRequest> httpEntity = new HttpEntity<>(request);
            ResponseEntity<Response> response = restTemplate.exchange( url, HttpMethod.PUT, httpEntity, Response.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                MapelResponse result = objectMapper.readValue(json, MapelResponse.class);
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
    public Optional<MapelResponse> delete(String id) {
        try {
            var url = Strings.concat(backEndUrl.mapelUrl(), "/"+ id);
            ResponseEntity<Response> response = restTemplate.exchange(url, HttpMethod.DELETE, null, Response.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                MapelResponse result = objectMapper.readValue(json, MapelResponse.class);
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
