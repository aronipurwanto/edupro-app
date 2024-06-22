package org.edupro.web.lookup.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.edupro.web.base.model.Response;
import org.edupro.web.base.service.BaseService;
import org.edupro.web.constant.BackEndUrl;
import org.edupro.web.constant.CommonConstant;
import org.edupro.web.exception.EduProWebException;
import org.edupro.web.lookup.model.LookupReq;
import org.edupro.web.lookup.model.LookupRes;
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

@Service
@RequiredArgsConstructor
public class LookupServiceImpl extends BaseService implements LookupService {
    private final BackEndUrl backEndUrl;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public List<LookupRes> getLookupData(String urlSuffix) {
        try {
            String url = backEndUrl.lookupUrl() + urlSuffix;
            ResponseEntity<Response> response = restTemplate.exchange(url, HttpMethod.GET, getHttpEntity(), Response.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                String json = objectMapper.writeValueAsString(response.getBody().getData());
                return CommonUtil.jsonArrayToList(json, LookupRes.class);
            }
            return Collections.emptyList();
        } catch (Exception e) {
            // Handle all exceptions in a single catch block
            // Consider logging the error for debugging purposes
            throw new EduProWebException(CommonConstant.Error.ERR_API, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<LookupRes> get() {
        return getLookupData("");
    }

    @Override
    public List<LookupRes> getByGroup(String group) {
        return getLookupData("/group/" + group);
    }

    @Override
    public List<LookupRes> getGroup() {
        return getLookupData("/group");
    }

    @Override
    public Optional<LookupRes> getById(String id) {
        try {
            var url = Strings.concat(backEndUrl.lookupUrl(), "/"+ id);
            ResponseEntity<Response> response = restTemplate.exchange( url, HttpMethod.GET, this.getHttpEntity(), Response.class);
            if(response.getStatusCode() == HttpStatus.OK) {
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                LookupRes result = objectMapper.readValue(json, LookupRes.class);

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
    public Optional<LookupRes> save(LookupReq request) {
        try{
            var url = backEndUrl.lookupUrl();
            HttpEntity<LookupReq> httpEntity = new HttpEntity<>(request, getHeader());
            ResponseEntity<Response> response = restTemplate.exchange( url, HttpMethod.POST, httpEntity, Response.class);
            if(response.getStatusCode() == HttpStatus.OK) {
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                LookupRes result = objectMapper.readValue(json, LookupRes.class);
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
    public Optional<LookupRes> update(LookupReq request, String id) {
        try{
            var url = Strings.concat(backEndUrl.lookupUrl(),"/"+ id);
            HttpEntity<LookupReq> httpEntity = new HttpEntity<>(request, getHeader());
            ResponseEntity<Response> response = restTemplate.exchange( url, HttpMethod.PUT, httpEntity, Response.class);
            if(response.getStatusCode() == HttpStatus.OK) {
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                LookupRes result = objectMapper.readValue(json, LookupRes.class);
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
    public Optional<LookupRes> delete(String id) {
        try{
            var url = Strings.concat(backEndUrl.lookupUrl(),"/"+ id);
            ResponseEntity<Response> response = restTemplate.exchange( url, HttpMethod.DELETE, this.getHttpEntity(), Response.class);
            if(response.getStatusCode() == HttpStatus.OK) {
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                LookupRes result = objectMapper.readValue(json, LookupRes.class);
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
