package org.edupro.web.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.edupro.web.constant.BackEndUrl;
import org.edupro.web.constant.CommonConstant;
import org.edupro.web.exception.EduProWebException;
import org.edupro.web.model.request.KelompokRequest;
import org.edupro.web.model.response.KelompokResponse;
import org.edupro.web.model.response.Response;
import org.edupro.web.service.MasterKelompokService;
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
public class MasterKelompokServiceImpl extends BaseService implements MasterKelompokService {
    private final BackEndUrl backEndUrl;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public List<KelompokResponse> get() {
        try {
            var url = backEndUrl.kelompokUrl();
            ResponseEntity<Response> response = restTemplate.getForEntity(url, Response.class);
            if(response.getStatusCode() == HttpStatus.OK) {
                return (List<KelompokResponse>) response.getBody().getData();
            }

            return Collections.emptyList();
        }catch (RestClientException e){
            var errors = this.readError(e);
            throw new EduProWebException(CommonConstant.Error.ERR_API, errors);
        }
    }

    @Override
    public Optional<KelompokResponse> getById(String id) {
        try {
            var url = Strings.concat(backEndUrl.kelompokUrl(), "/" + id);
            ResponseEntity<Response> response = restTemplate.getForEntity(url, Response.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                KelompokResponse result = objectMapper.readValue(json, KelompokResponse.class);

                return Optional.of(result);
            }

            return Optional.empty();
        } catch (RestClientException e){
            var errors = this.readError(e);
            throw new EduProWebException(CommonConstant.Error.ERR_API, errors);
        }catch (IOException e) {
            List<FieldError> errors = List.of(new FieldError("id", "id", e.getMessage()));
            throw new EduProWebException(CommonConstant.Error.ERR_API, errors);
        }
    }

    @Override
    public Optional<KelompokResponse> save(KelompokRequest request) {
        try {
            var url = backEndUrl.kelompokUrl();
            HttpEntity<KelompokRequest> httpEntity = new HttpEntity<>(request);
            ResponseEntity<Response> response = restTemplate.postForEntity(url, httpEntity, Response.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                KelompokResponse result = objectMapper.readValue(json, KelompokResponse.class);

                return Optional.of(result);
            }

            return Optional.empty();
        } catch (RestClientException e){
            var errors = this.readError(e);
            throw new EduProWebException(CommonConstant.Error.ERR_API, errors);
        }catch (IOException e) {
            List<FieldError> errors = List.of(new FieldError("id", "id", e.getMessage()));
            throw new EduProWebException(CommonConstant.Error.ERR_API, errors);
        }
    }

    @Override
    public Optional<KelompokResponse> update(KelompokRequest request, String id) {
        try {
            var url = Strings.concat(backEndUrl.kelompokUrl(), "/" + id);
            HttpEntity<KelompokRequest> httpEntity = new HttpEntity<>(request);
            ResponseEntity<Response> response = restTemplate.exchange(url, HttpMethod.PUT, httpEntity, Response.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                KelompokResponse result = objectMapper.readValue(json, KelompokResponse.class);

                return Optional.of(result);
            }

            return Optional.empty();
        } catch (RestClientException e){
            var errors = this.readError(e);
            throw new EduProWebException(CommonConstant.Error.ERR_API, errors);
        }catch (IOException e) {
            List<FieldError> errors = List.of(new FieldError("id", "id", e.getMessage()));
            throw new EduProWebException(CommonConstant.Error.ERR_API, errors);
        }
    }

    @Override
    public Optional<KelompokResponse> delete(String id) {
        try{
            var url = Strings.concat(backEndUrl.kelompokUrl(), "/" + id);
            ResponseEntity<Response> response = restTemplate.exchange( url, HttpMethod.DELETE, null, Response.class);
            if(response.getStatusCode() == HttpStatus.OK) {
                byte[] json = objectMapper.writeValueAsBytes(Objects.requireNonNull(response.getBody()).getData());
                KelompokResponse result = objectMapper.readValue(json, KelompokResponse.class);

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
}
