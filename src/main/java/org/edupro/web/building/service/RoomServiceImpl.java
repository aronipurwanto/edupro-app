package org.edupro.web.building.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.edupro.web.building.model.RoomReq;
import org.edupro.web.building.model.RoomRes;
import org.edupro.web.constant.BackEndUrl;
import org.edupro.web.constant.CommonConstant;
import org.edupro.web.exception.EduProWebException;
import org.edupro.web.base.model.Response;
import org.edupro.web.base.service.BaseService;
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
public class RoomServiceImpl extends BaseService implements RoomService {
    private final BackEndUrl backEndUrl;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

     @Override
     public List<RoomRes> get() {
         try {
             var url = backEndUrl.ruanganUrl();
             ResponseEntity<Response> response = restTemplate.exchange( url, HttpMethod.GET, this.getHttpEntity(), Response.class);
             if (response.getStatusCode() == HttpStatus.OK) {
                 return (List<RoomRes>) response.getBody().getData();
             }

             return Collections.emptyList();
         } catch (RestClientException e){
             var errors = this.readError(e);
             throw new EduProWebException(CommonConstant.Error.ERR_API, errors);
         }
    }

    @Override
    public Optional<RoomRes> getById(String id) {
         try {
             var url = Strings.concat(backEndUrl.ruanganUrl(), "/" + id );
             ResponseEntity<Response> response = restTemplate.exchange( url, HttpMethod.GET, this.getHttpEntity(), Response.class);
             if (response.getStatusCode() == HttpStatus.OK) {
                 byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                 RoomRes result = objectMapper.readValue(json, RoomRes.class);

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
    public Optional<RoomRes> save(RoomReq request) {
         try {
             var url = backEndUrl.ruanganUrl();
             HttpEntity<RoomReq> httpEntity = new HttpEntity<>(request, getHeader());
             ResponseEntity<Response> response = restTemplate.postForEntity(url, httpEntity, Response.class);
             if (response.getStatusCode() == HttpStatus.OK) {
                 byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                 RoomRes result = objectMapper.readValue(json, RoomRes.class);
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
    public Optional<RoomRes> update(RoomReq request, String id) {
        try {
            var url = Strings.concat(backEndUrl.ruanganUrl(), "/" + id);
            HttpEntity<RoomReq> httpEntity = new HttpEntity<>(request, getHeader());
            ResponseEntity<Response> response = restTemplate.exchange(url, HttpMethod.PUT, httpEntity, Response.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                RoomRes result = objectMapper.readValue(json, RoomRes.class);

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
    public Optional<RoomRes> delete(String id) {
        try {
            var url = Strings.concat(backEndUrl.ruanganUrl(), "/" + id);
            ResponseEntity<Response> response = restTemplate.exchange( url, HttpMethod.DELETE, this.getHttpEntity(), Response.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                RoomRes result = objectMapper.readValue(json, RoomRes.class);

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
