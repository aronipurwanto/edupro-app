package org.edupro.web.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.edupro.web.constant.BackEndUrl;
import org.edupro.web.constant.CommonConstant;
import org.edupro.web.exception.EduProWebException;
import org.edupro.web.model.request.SiswaRequest;
import org.edupro.web.model.response.Response;
import org.edupro.web.model.response.SiswaResponse;
import org.edupro.web.service.MasterSiswaService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MasterSiswaServiceImpl extends BaseService implements MasterSiswaService {
    private final BackEndUrl backEndUrl;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

     @Override
     public List<SiswaResponse> get() throws EduProWebException {
         try {
             var url = backEndUrl.siswaUrl();
             ResponseEntity<Response> response = restTemplate.exchange( url, HttpMethod.GET, getHttpEntity(), Response.class);
             if (response.getStatusCode() == HttpStatus.OK) {
                 var data = (List<SiswaResponse>) response.getBody().getData();
                 return data;
             }
             return Collections.emptyList();
         } catch (RestClientException e){
             var errors = this.readError(e);
             throw new EduProWebException(CommonConstant.Error.ERR_API, errors);
         }
    }

    @Override
    public Optional<SiswaResponse> getById(String id) throws EduProWebException{
         try {
             var url = Strings.concat(backEndUrl.siswaUrl(), "/" + id);
             ResponseEntity<Response> response = restTemplate.exchange( url, HttpMethod.GET, getHttpEntity(), Response.class);
             if (response.getStatusCode() == HttpStatus.OK) {
                 byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                 SiswaResponse result = objectMapper.readValue(json, SiswaResponse.class);

                 return Optional.of(result);
             }
             return Optional.empty();
         }catch (RestClientException e){
             var errors = this.readError(e);
             throw new EduProWebException(CommonConstant.Error.ERR_API, errors);
         }catch (IOException e) {
             List<FieldError> errors = List.of(new FieldError("id", id, e.getMessage()));
             throw new EduProWebException(CommonConstant.Error.ERR_API, errors);
         }
    }

    @Override
    public Optional<SiswaResponse> save(SiswaRequest request) throws EduProWebException {
         try {
             var url = backEndUrl.siswaUrl();
             HttpEntity<SiswaRequest> httpEntity = new HttpEntity<>(request, this.getHeader());
             ResponseEntity<Response> response = restTemplate.exchange( url, HttpMethod.POST, httpEntity, Response.class);
             if (response.getStatusCode() == HttpStatus.OK) {
                 byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                 SiswaResponse result = objectMapper.readValue(json, SiswaResponse.class);
                 return Optional.of(result);
             }
             return Optional.empty();
         }catch (RestClientException e){
             var errors = this.readError(e);
             throw new EduProWebException(CommonConstant.Error.ERR_API, errors);
         }catch (IOException e) {
             List<FieldError> errors = List.of(new FieldError("id", "id", e.getMessage()));
             throw new EduProWebException(CommonConstant.Error.ERR_API, errors);
         }
    }

    @Override
    public Optional<SiswaResponse> update(SiswaRequest request, String id) {
        try {
            var url = Strings.concat(backEndUrl.siswaUrl(), "/" + id);
            HttpEntity<SiswaRequest> httpEntity = new HttpEntity<>(request, getHeader());
            ResponseEntity<Response> response = restTemplate.exchange(url, HttpMethod.PUT, httpEntity, Response.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                SiswaResponse result = objectMapper.readValue(json, SiswaResponse.class);

                return Optional.of(result);
            }
            return Optional.empty();
        }catch (RestClientException e){
            var errors = this.readError(e);
            throw new EduProWebException(CommonConstant.Error.ERR_API, errors);
        }catch (IOException e) {
            List<FieldError> errors = List.of(new FieldError("id", id, e.getMessage()));
            throw new EduProWebException(CommonConstant.Error.ERR_API, errors);
        }
    }

    @Override
    public Optional<SiswaResponse> delete(String id) {
        try {
            var url = Strings.concat(backEndUrl.siswaUrl(), "/" + id);
            ResponseEntity<Response> response = restTemplate.exchange(url, HttpMethod.DELETE, getHttpEntity(), Response.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                SiswaResponse result = objectMapper.readValue(json, SiswaResponse.class);

                return Optional.of(result);
            }
            return Optional.empty();
        }catch (RestClientException e){
            var errors = this.readError(e);
            throw new EduProWebException(CommonConstant.Error.ERR_API, errors);
        }catch (IOException e) {
            List<FieldError> errors = List.of(new FieldError("id", id, e.getMessage()));
            throw new EduProWebException(CommonConstant.Error.ERR_API, errors);
        }
    }
}
