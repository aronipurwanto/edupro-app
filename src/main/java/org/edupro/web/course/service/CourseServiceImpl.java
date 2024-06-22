package org.edupro.web.course.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.logging.log4j.util.Strings;
import org.edupro.web.base.model.Response;
import org.edupro.web.constant.BackEndUrl;
import org.edupro.web.constant.CommonConstant;
import org.edupro.web.course.model.*;
import org.edupro.web.exception.EduProWebException;
import org.edupro.web.base.service.BaseService;
import org.edupro.web.util.CommonUtil;
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

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseServiceImpl extends BaseService implements CourseService {
    private final BackEndUrl backEndUrl;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    private List<CourseRes> getCourseResponses(String url) throws IOException {
        ResponseEntity<Response> response = restTemplate.exchange( url, HttpMethod.GET, this.getHttpEntity(), Response.class);
        if (response.getStatusCode() == HttpStatus.OK){
            String json = objectMapper.writeValueAsString(Objects.requireNonNull(response.getBody()).getData());
            return CommonUtil.jsonArrayToList(json, CourseRes.class);
        }
        return Collections.emptyList();
    }

    @Override
    public List<CourseRes> get() throws EduProWebException {
        try {
            var url = backEndUrl.courseUrl();
            return getCourseResponses(url);
        }catch (RestClientException e){
            var errors = this.readError(e);
            throw new EduProWebException(CommonConstant.Error.ERR_API, errors);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<CoursePeopleRes> getPeople(String id) {
            var url = backEndUrl.courseUrl()+"/"+id+"/people";
            return getCoursePeople(url);
    }

    @Override
    public List<CourseRes> getByUser() throws EduProWebException {
        try {
            var url = backEndUrl.courseUrl()+"/user";
            return getCourseResponses(url);
        }catch (RestClientException e){
            var errors = this.readError(e);
            throw new EduProWebException(CommonConstant.Error.ERR_API, errors);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CourseSectionRes> getTopicByCourseId(String courseId) throws EduProWebException {
        var url = backEndUrl.courseUrl()+"/"+courseId+"/topic";
        return getSection(url);
    }

    @Override
    public List<CourseSectionRes> getSectionByCourseId(String courseId) throws EduProWebException {
        var url = backEndUrl.courseUrl()+"/"+courseId+"/section";
        return getSection(url);
    }

    @Override
    public Optional<CourseSectionRes> saveSection(CourseSectionReq request) throws EduProWebException {
        try {
            var url = backEndUrl.courseUrl()+"/"+request.getCourseId()+"/section";
            HttpEntity<CourseSectionReq> httpEntity = new HttpEntity<>(request, getHeader());
            ResponseEntity<Response> response = restTemplate.postForEntity(url, httpEntity, Response.class);
            if (response.getStatusCode() == HttpStatus.OK){
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                CourseSectionRes result = objectMapper.readValue(json, CourseSectionRes.class);
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
    public Optional<CourseSiswaResponse> saveSiswa(CourseSiswaRequest request) {
        try {
            var url = backEndUrl.courseUrl()+"/"+request.getCourseId()+"/siswa";
            HttpEntity<CourseSiswaRequest> httpEntity = new HttpEntity<>(request, getHeader());
            ResponseEntity<Response> response = restTemplate.postForEntity(url, httpEntity, Response.class);
            if (response.getStatusCode() == HttpStatus.OK){
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                CourseSiswaResponse result = objectMapper.readValue(json, CourseSiswaResponse.class);
                return Optional.of(result);
            }

            return Optional.empty();
        }catch (RestClientException e){
            var errors = this.readError(e);
            throw  new EduProWebException(CommonConstant.Error.ERR_API, errors);
        }catch (IOException e){
            List<FieldError> errors = List.of(new FieldError("id", "id", e.getMessage()));
            throw new EduProWebException(CommonConstant.Error.ERR_API, errors);
        }
    }

    private List<CourseSectionRes> getSection(String url) throws EduProWebException {
        try {
            ResponseEntity<Response> response = restTemplate.exchange( url, HttpMethod.GET, this.getHttpEntity(), Response.class);
            if (response.getStatusCode() == HttpStatus.OK){
                String json = objectMapper.writeValueAsString(Objects.requireNonNull(response.getBody()).getData());
                List<CourseSectionRes> result = CommonUtil.jsonArrayToList(json, CourseSectionRes.class);
                return result;
            }
            return Collections.emptyList();
        }catch (RestClientException e){
            var errors = this.readError(e);
            throw new EduProWebException(CommonConstant.Error.ERR_API, errors);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Optional<CoursePeopleRes> getCoursePeople(String url) throws EduProWebException {
        try {
            ResponseEntity<Response> response = restTemplate.exchange( url, HttpMethod.GET, this.getHttpEntity(), Response.class);
            if (response.getStatusCode() == HttpStatus.OK){
                String json = objectMapper.writeValueAsString(Objects.requireNonNull(response.getBody()).getData());
                CoursePeopleRes result = objectMapper.readValue(json, CoursePeopleRes.class);
                return Optional.of(result);
            }
            return Optional.empty();
        }catch (RestClientException e){
            var errors = this.readError(e);
            throw new EduProWebException(CommonConstant.Error.ERR_API, errors);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<CourseRes> getById(String id) throws EduProWebException{
        try {
            var url = Strings.concat(backEndUrl.courseUrl(), "/" + id);
            ResponseEntity<Response> response = restTemplate.exchange( url, HttpMethod.GET, this.getHttpEntity(), Response.class);
            if (response.getStatusCode() == HttpStatus.OK){
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                CourseRes result = objectMapper.readValue(json, CourseRes.class);

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
    public Optional<CourseRes> save(CourseReq request) {
        try {
            var url = backEndUrl.courseUrl();
            HttpEntity<CourseReq> httpEntity = new HttpEntity<>(request, getHeader());
            ResponseEntity<Response> response = restTemplate.postForEntity(url, httpEntity, Response.class);
            if (response.getStatusCode() == HttpStatus.OK){
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                CourseRes result = objectMapper.readValue(json, CourseRes.class);
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
    public Optional<CourseRes> update(CourseReq courseReq, String id) {
        try {
            var url = Strings.concat(backEndUrl.courseUrl(), "/" + id);
            HttpEntity<CourseReq> httpEntity = new HttpEntity<>(courseReq, getHeader());
            ResponseEntity<Response> response = restTemplate.exchange(url, HttpMethod.PUT, httpEntity, Response.class);
            if (response.getStatusCode() == HttpStatus.OK){
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                CourseRes result = objectMapper.readValue(json, CourseRes.class);
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
    public Optional<CourseRes> delete(String id) {
        try {
            var url = Strings.concat(backEndUrl.courseUrl(), "/" + id);
            ResponseEntity<Response> response = restTemplate.exchange( url, HttpMethod.DELETE, this.getHttpEntity(), Response.class);
            if (response.getStatusCode() == HttpStatus.OK){
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                CourseRes result = objectMapper.readValue(json, CourseRes.class);
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
