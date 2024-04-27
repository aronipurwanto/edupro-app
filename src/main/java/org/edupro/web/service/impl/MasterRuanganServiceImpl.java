package org.edupro.web.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.edupro.web.constant.BackEndUrl;
import org.edupro.web.model.request.RuanganRequest;
import org.edupro.web.model.response.Response;
import org.edupro.web.model.response.RuanganResponse;
import org.edupro.web.service.MasterRuanganService;
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
public class MasterRuanganServiceImpl implements MasterRuanganService {
    private final BackEndUrl backEndUrl;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

     @Override
     public List<RuanganResponse> get() {
         try {
             var url = backEndUrl.ruanganUrl();
             ResponseEntity<Response> response = restTemplate.getForEntity(url, Response.class);
             if (response.getStatusCode() == HttpStatus.OK) {
                 return (List<RuanganResponse>) response.getBody().getData();
             }
         } catch (RestClientException e){
             return Collections.emptyList();
         }

         return Collections.emptyList();
    }

    @Override
    public Optional<RuanganResponse> getById(String id) {
         try {
             var url = Strings.concat(backEndUrl.ruanganUrl(), "/" + id );
             ResponseEntity<Response> response = restTemplate.getForEntity(url, Response.class);
             if (response.getStatusCode() == HttpStatus.OK) {
                 byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                 RuanganResponse result = objectMapper.readValue(json, RuanganResponse.class);

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
    public Optional<RuanganResponse> save(RuanganRequest request) {
         try {
             var url = backEndUrl.ruanganUrl();
             HttpEntity<RuanganRequest> httpEntity = new HttpEntity<>(request);
             ResponseEntity<Response> response = restTemplate.postForEntity(url, httpEntity, Response.class);
             if (response.getStatusCode() == HttpStatus.OK) {
                 byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                 RuanganResponse result = objectMapper.readValue(json, RuanganResponse.class);
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
    public Optional<RuanganResponse> update(RuanganRequest request, String id) {
        try {
            var url = Strings.concat(backEndUrl.ruanganUrl(), "/" + id);
            HttpEntity<RuanganRequest> httpEntity = new HttpEntity<>(request);
            ResponseEntity<Response> response = restTemplate.exchange(url, HttpMethod.PUT, httpEntity, Response.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                RuanganResponse result = objectMapper.readValue(json, RuanganResponse.class);

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
    public Optional<RuanganResponse> delete(String id) {
        try {
            var url = Strings.concat(backEndUrl.ruanganUrl(), "/" + id);
            ResponseEntity<Response> response = restTemplate.exchange(url, HttpMethod.DELETE, null, Response.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                RuanganResponse result = objectMapper.readValue(json, RuanganResponse.class);

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
