package org.edupro.web.institution.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.edupro.web.base.service.BaseService;
import org.edupro.web.constant.BaseApiUrl;
import org.edupro.web.institution.model.InstitutionReq;
import org.edupro.web.institution.model.InstitutionRes;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class InstitutionServiceImpl extends BaseService<InstitutionRes, InstitutionReq> implements InstitutionService {
    public InstitutionServiceImpl(String backEndUrl, RestTemplate restTemplate, ObjectMapper objectMapper) {
        super(backEndUrl+ BaseApiUrl.INSTITUTION_URL, restTemplate, objectMapper);
    }

    @Override
    public List<InstitutionRes> get() {
        return super.get(InstitutionRes.class);
    }

    @Override
    public Optional<InstitutionRes> getById(String id) {
        return super.getById(id, InstitutionRes.class);
    }

    @Override
    public Optional<InstitutionRes> save(InstitutionReq request) {
        return super.save(request, InstitutionRes.class);
    }


    @Override
    public Optional<InstitutionRes> update(InstitutionReq request, String id) {
        return super.update(request, id, InstitutionRes.class);
    }

    @Override
    public Optional<InstitutionRes> delete(String id) {
        return super.delete(id, InstitutionRes.class);
    }
}
