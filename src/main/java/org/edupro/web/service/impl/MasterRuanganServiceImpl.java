package org.edupro.web.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.edupro.web.constant.BackEndUrl;
import org.edupro.web.constant.CommonConstant;
import org.edupro.web.exception.EduProWebException;
import org.edupro.web.model.request.RuanganRequest;
import org.edupro.web.model.response.Response;
import org.edupro.web.model.response.RuanganResponse;
import org.edupro.web.service.MasterRuanganService;
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
public class MasterRuanganServiceImpl extends BaseService implements MasterRuanganService {
    private final BackEndUrl backEndUrl;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

     @Override
     public List<RuanganResponse> get() {
         try {
             var url = backEndUrl.ruanganUrl();
             ResponseEntity<Response> response = restTemplate.exchange( url, HttpMethod.GET, this.getHttpEntity(), Response.class);
             if (response.getStatusCode() == HttpStatus.OK) {
                 return (List<RuanganResponse>) response.getBody().getData();
             }

             return Collections.emptyList();
         } catch (RestClientException e){
             var errors = this.readError(e);
             throw new EduProWebException(CommonConstant.Error.ERR_API, errors);
         }
    }

    @Override
    public Optional<RuanganResponse> getById(String id) {
         try {
             var url = Strings.concat(backEndUrl.ruanganUrl(), "/" + id );
             ResponseEntity<Response> response = restTemplate.exchange( url, HttpMethod.GET, this.getHttpEntity(), Response.class);
             if (response.getStatusCode() == HttpStatus.OK) {
                 byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                 RuanganResponse result = objectMapper.readValue(json, RuanganResponse.class);

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
    public Optional<RuanganResponse> save(RuanganRequest request) {
         try {
             var url = backEndUrl.ruanganUrl();
             HttpEntity<RuanganRequest> httpEntity = new HttpEntity<>(request, getHeader());
             ResponseEntity<Response> response = restTemplate.postForEntity(url, httpEntity, Response.class);
             if (response.getStatusCode() == HttpStatus.OK) {
                 byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                 RuanganResponse result = objectMapper.readValue(json, RuanganResponse.class);
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
    public Optional<RuanganResponse> update(RuanganRequest request, String id) {
        try {
            var url = Strings.concat(backEndUrl.ruanganUrl(), "/" + id);
            HttpEntity<RuanganRequest> httpEntity = new HttpEntity<>(request, getHeader());
            ResponseEntity<Response> response = restTemplate.exchange(url, HttpMethod.PUT, httpEntity, Response.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                RuanganResponse result = objectMapper.readValue(json, RuanganResponse.class);

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
    public Optional<RuanganResponse> delete(String id) {
        try {
            var url = Strings.concat(backEndUrl.ruanganUrl(), "/" + id);
            ResponseEntity<Response> response = restTemplate.exchange( url, HttpMethod.DELETE, this.getHttpEntity(), Response.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                RuanganResponse result = objectMapper.readValue(json, RuanganResponse.class);

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
