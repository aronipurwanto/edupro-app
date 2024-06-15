package org.edupro.web.student.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.edupro.web.constant.BackEndUrl;
import org.edupro.web.constant.CommonConstant;
import org.edupro.web.exception.EduProWebException;
import org.edupro.web.student.model.StudentReq;
import org.edupro.web.base.model.Response;
import org.edupro.web.student.model.StudentRes;
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
public class StudentServiceImpl extends BaseService implements StudentService {
    private final BackEndUrl backEndUrl;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

     @Override
     public List<StudentRes> get() throws EduProWebException {
         try {
             var url = backEndUrl.siswaUrl();
             ResponseEntity<Response> response = restTemplate.exchange( url, HttpMethod.GET, getHttpEntity(), Response.class);
             if (response.getStatusCode() == HttpStatus.OK) {
                 var data = (List<StudentRes>) response.getBody().getData();
                 return data;
             }
             return Collections.emptyList();
         } catch (RestClientException e){
             var errors = this.readError(e);
             throw new EduProWebException(CommonConstant.Error.ERR_API, errors);
         }
    }

    @Override
    public Optional<StudentRes> getById(String id) throws EduProWebException{
         try {
             var url = Strings.concat(backEndUrl.siswaUrl(), "/" + id);
             ResponseEntity<Response> response = restTemplate.exchange( url, HttpMethod.GET, getHttpEntity(), Response.class);
             if (response.getStatusCode() == HttpStatus.OK) {
                 byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                 StudentRes result = objectMapper.readValue(json, StudentRes.class);

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
    public Optional<StudentRes> save(StudentReq request) throws EduProWebException {
         try {
             var url = backEndUrl.siswaUrl();
             HttpEntity<StudentReq> httpEntity = new HttpEntity<>(request, this.getHeader());
             ResponseEntity<Response> response = restTemplate.exchange( url, HttpMethod.POST, httpEntity, Response.class);
             if (response.getStatusCode() == HttpStatus.OK) {
                 byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                 StudentRes result = objectMapper.readValue(json, StudentRes.class);
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
    public Optional<StudentRes> update(StudentReq request, String id) {
        try {
            var url = Strings.concat(backEndUrl.siswaUrl(), "/" + id);
            HttpEntity<StudentReq> httpEntity = new HttpEntity<>(request, getHeader());
            ResponseEntity<Response> response = restTemplate.exchange(url, HttpMethod.PUT, httpEntity, Response.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                StudentRes result = objectMapper.readValue(json, StudentRes.class);

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
    public Optional<StudentRes> delete(String id) {
        try {
            var url = Strings.concat(backEndUrl.siswaUrl(), "/" + id);
            ResponseEntity<Response> response = restTemplate.exchange(url, HttpMethod.DELETE, getHttpEntity(), Response.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                StudentRes result = objectMapper.readValue(json, StudentRes.class);

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
