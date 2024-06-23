package org.edupro.web.curriculum.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.edupro.web.base.service.BaseService;
import org.edupro.web.curriculum.model.CurriculumReq;
import org.edupro.web.curriculum.model.CurriculumRes;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class CurriculumServiceImpl extends BaseService<CurriculumRes, CurriculumReq> implements CurriculumService {

    public CurriculumServiceImpl(String backEndUrl, RestTemplate restTemplate, ObjectMapper objectMapper) {
        super(backEndUrl, restTemplate, objectMapper);
    }

    @Override
    public List<CurriculumRes> get() {
        return super.get(CurriculumRes.class);
    }

    @Override
    public Optional<CurriculumRes> getById(String id) {
        return super.getById(id, CurriculumRes.class);
    }

    @Override
    public Optional<CurriculumRes> save(CurriculumReq request) {
        return super.save(request, CurriculumRes.class);
    }


    @Override
    public Optional<CurriculumRes> update(CurriculumReq request, String id) {
        return super.update(request, id, CurriculumRes.class);
    }

    @Override
    public Optional<CurriculumRes> delete(String id) {
       return super.delete(id, CurriculumRes.class);
    }
}
