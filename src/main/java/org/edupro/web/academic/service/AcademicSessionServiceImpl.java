package org.edupro.web.academic.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.edupro.web.academic.model.AcademicSessionReq;
import org.edupro.web.academic.model.AcademicSessionRes;
import org.edupro.web.base.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class AcademicSessionServiceImpl extends BaseService<AcademicSessionRes,AcademicSessionReq> implements AcademicSessionService {

    public AcademicSessionServiceImpl(String backEndUrl, RestTemplate restTemplate, ObjectMapper objectMapper) {
        super(backEndUrl, restTemplate, objectMapper);
    }

    @Override
    public List<AcademicSessionRes> get() {
        return super.get(AcademicSessionRes.class);
    }

    @Override
    public Optional<AcademicSessionRes> getById(String id) {
        return super.getById(id, AcademicSessionRes.class);
    }

    @Override
    public Optional<AcademicSessionRes> save(AcademicSessionReq request) {
        return super.save(request, AcademicSessionRes.class);
    }

    @Override
    public Optional<AcademicSessionRes> update(AcademicSessionReq request, String id) {
        return super.update(request, id, AcademicSessionRes.class);
    }

    @Override
    public Optional<AcademicSessionRes> delete(String id) {
        return super.delete(id, AcademicSessionRes.class);
    }
}
