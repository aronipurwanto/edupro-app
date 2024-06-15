package org.edupro.web.curriculum.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.edupro.web.constant.BackEndUrl;
import org.edupro.web.constant.CommonConstant;
import org.edupro.web.curriculum.model.CurriculumReq;
import org.edupro.web.curriculum.model.CurriculumRes;
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
public class CurriculumServiceImpl extends BaseService implements CurriculumService {
    private final BackEndUrl backEndUrl;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    
    @Override
    public List<CurriculumRes> get() {
        try {
            var url = backEndUrl.kurikulumUrl();
            ResponseEntity<Response> response = restTemplate.exchange( url, HttpMethod.GET, this.getHttpEntity(), Response.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                return (List<CurriculumRes>) response.getBody().getData();
            }

            return Collections.emptyList();
        }catch (RestClientException e){
            var errors = this.readError(e);
            throw new EduProWebException(CommonConstant.Error.ERR_API, errors);
        }
    }

    @Override
    public Optional<CurriculumRes> getById(String id) {
        try {
            var url = Strings.concat(backEndUrl.kurikulumUrl(), "/"+ id);
            ResponseEntity<Response> response = restTemplate.exchange( url, HttpMethod.GET, this.getHttpEntity(), Response.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                CurriculumRes result = objectMapper.readValue(json, CurriculumRes.class);

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
    public Optional<CurriculumRes> save(CurriculumReq request) {
        try {
            var url = backEndUrl.kurikulumUrl();
            HttpEntity<CurriculumReq> httpEntity = new HttpEntity<>(request, getHeader());
            ResponseEntity<Response> response = restTemplate.postForEntity( url, httpEntity, Response.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                CurriculumRes result = objectMapper.readValue(json, CurriculumRes.class);
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
    public Optional<CurriculumRes> update(CurriculumReq request, String id) {
        try {
            var url = Strings.concat(backEndUrl.kurikulumUrl(),"/"+ id);
            HttpEntity<CurriculumReq> httpEntity = new HttpEntity<>(request, getHeader());
            ResponseEntity<Response> response = restTemplate.exchange( url, HttpMethod.PUT, httpEntity, Response.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                CurriculumRes result = objectMapper.readValue(json, CurriculumRes.class);
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
    public Optional<CurriculumRes> delete(String id) {
        try {
            var url = Strings.concat(backEndUrl.kurikulumUrl(),"/"+ id);
            ResponseEntity<Response> response = restTemplate.exchange( url, HttpMethod.DELETE, this.getHttpEntity(), Response.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                CurriculumRes result = objectMapper.readValue(json, CurriculumRes.class);
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
