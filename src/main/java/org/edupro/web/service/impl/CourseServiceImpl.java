package org.edupro.web.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.edupro.web.constant.BackEndUrl;
import org.edupro.web.constant.CommonConstant;
import org.edupro.web.exception.EduProWebException;
import org.edupro.web.model.request.CourseRequest;
import org.edupro.web.model.response.CourseResponse;
import org.edupro.web.model.response.Response;
import org.edupro.web.service.CourseService;
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
public class CourseServiceImpl extends BaseService implements CourseService {
    private final BackEndUrl backEndUrl;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public List<CourseResponse> get() {
        try {
            var url = backEndUrl.courseUrl();
            ResponseEntity<Response> response = restTemplate.exchange(url, HttpMethod.GET, this.getHttpEntity(), Response.class);
            if (response.getStatusCode() == HttpStatus.OK){
                return (List<CourseResponse>) response.getBody().getData();
            }
            return Collections.emptyList();
        }catch (RestClientException e){
            var errors = this.readError(e);
            throw new EduProWebException(CommonConstant.Error.ERR_API, errors);
        }
    }

    @Override
    public Optional<CourseResponse> getById(String id) {
        try {
            var url = Strings.concat(backEndUrl.courseUrl(), "/" + id);
            ResponseEntity<Response> response = restTemplate.getForEntity(url, Response.class);
            if (response.getStatusCode() == HttpStatus.OK){
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                CourseResponse result = objectMapper.readValue(json, CourseResponse.class);

                return Optional.of(result);
            }
        }catch (RestClientException e){
            var errors = this.readError(e);
            throw new EduProWebException(CommonConstant.Error.ERR_API, errors);
        }catch (IOException e) {
            List<FieldError> errors = List.of(new FieldError("id", id, e.getMessage()));
            throw new EduProWebException(CommonConstant.Error.ERR_API, errors);
        }

        return Optional.empty();
    }

    @Override
    public Optional<CourseResponse> save(CourseRequest request) {
        try {
            var url = backEndUrl.courseUrl();
            HttpEntity<CourseRequest> httpEntity = new HttpEntity<>(request);
            ResponseEntity<Response> response = restTemplate.postForEntity(url, httpEntity, Response.class);
            if (response.getStatusCode() == HttpStatus.OK){
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                CourseResponse result = objectMapper.readValue(json, CourseResponse.class);
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
    public Optional<CourseResponse> update(CourseRequest courseRequest, String id) {
        try {
            var url = Strings.concat(backEndUrl.courseUrl(), "/" + id);
            HttpEntity<CourseRequest> httpEntity = new HttpEntity<>(courseRequest);
            ResponseEntity<Response> response = restTemplate.exchange(url, HttpMethod.PUT, httpEntity, Response.class);
            if (response.getStatusCode() == HttpStatus.OK){
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                CourseResponse result = objectMapper.readValue(json, CourseResponse.class);
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
    public Optional<CourseResponse> delete(String id) {
        try {
            var url = Strings.concat(backEndUrl.courseUrl(), "/" + id);
            ResponseEntity<Response> response = restTemplate.exchange(url, HttpMethod.DELETE, null, Response.class);
            if (response.getStatusCode() == HttpStatus.OK){
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                CourseResponse result = objectMapper.readValue(json, CourseResponse.class);
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
