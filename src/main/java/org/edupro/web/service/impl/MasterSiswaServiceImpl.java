package org.edupro.web.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.edupro.web.constant.BackEndUrl;
import org.edupro.web.model.request.SiswaRequest;
import org.edupro.web.model.response.Response;
import org.edupro.web.model.response.SiswaResponse;
import org.edupro.web.service.MasterSiswaService;
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
public class MasterSiswaServiceImpl implements MasterSiswaService {
    private final BackEndUrl backEndUrl;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

     @Override
     public List<SiswaResponse> get() {
         try {
             var url = backEndUrl.siswaUrl();
             ResponseEntity<Response> response = restTemplate.getForEntity(url, Response.class);
             if (response.getStatusCode() == HttpStatus.OK) {
                 return (List<SiswaResponse>) response.getBody().getData();
             }
         } catch (RestClientException e){
             return Collections.emptyList();
         }

         return Collections.emptyList();
    }

    @Override
    public Optional<SiswaResponse> getById(String id) {
         try {
             var url = Strings.concat(backEndUrl.siswaUrl(), "/" + id);
             ResponseEntity<Response> response = restTemplate.getForEntity(url, Response.class);
             if (response.getStatusCode() == HttpStatus.OK) {
                 byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                 SiswaResponse result = objectMapper.readValue(json, SiswaResponse.class);

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
    public Optional<SiswaResponse> save(SiswaRequest request) {
         try {
             var url = backEndUrl.siswaUrl();
             HttpEntity<SiswaRequest> httpEntity = new HttpEntity<>(request);
             ResponseEntity<Response> response = restTemplate.postForEntity(url, httpEntity, Response.class);
             if (response.getStatusCode() == HttpStatus.OK) {
                 byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                 SiswaResponse result = objectMapper.readValue(json, SiswaResponse.class);
                 return Optional.of(result);
             }
         }catch (RestClientException e){
             return Optional.empty();
         }catch (IOException e) {
             throw new RuntimeException(e);
         }
         return Optional.empty();
    }

    @Override
    public Optional<SiswaResponse> update(SiswaRequest request) {
        try {
            var url = Strings.concat(backEndUrl.siswaUrl(), "/" + request.getId());
            HttpEntity<SiswaRequest> httpEntity = new HttpEntity<>(request);
            ResponseEntity<Response> response = restTemplate.exchange(url, HttpMethod.PUT, httpEntity, Response.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                SiswaResponse result = objectMapper.readValue(json, SiswaResponse.class);

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
    public Optional<SiswaResponse> delete(SiswaRequest request) {
        try {
            var url = Strings.concat(backEndUrl.siswaUrl(), "/" + request.getId());
            ResponseEntity<Response> response = restTemplate.exchange(url, HttpMethod.DELETE, null, Response.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                SiswaResponse result = objectMapper.readValue(json, SiswaResponse.class);

                return Optional.of(result);
            }
        }catch (RestClientException e){
            return Optional.empty();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
}
