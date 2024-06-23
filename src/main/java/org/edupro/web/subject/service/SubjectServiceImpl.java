package org.edupro.web.subject.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.edupro.web.base.service.BaseService;
import org.edupro.web.constant.BaseApiUrl;
import org.edupro.web.subject.model.SubjectReq;
import org.edupro.web.subject.model.SubjectRes;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectServiceImpl extends BaseService<SubjectRes, SubjectReq> implements SubjectService {

    public SubjectServiceImpl(String backEndUrl, RestTemplate restTemplate, ObjectMapper objectMapper) {
        super(backEndUrl+ BaseApiUrl.SUBJECT_URL, restTemplate, objectMapper);
    }

    @Override
    public List<SubjectRes> get() {
        return super.get(SubjectRes.class);
    }

    @Override
    public Optional<SubjectRes> getById(String id) {
        return super.getById(id, SubjectRes.class);
    }

    @Override
    public Optional<SubjectRes> save(SubjectReq request) {
        return super.save(request, SubjectRes.class);
    }

    @Override
    public Optional<SubjectRes> update(SubjectReq request, String id) {
        return super.update(request, id, SubjectRes.class);
    }

    @Override
    public Optional<SubjectRes> delete(String id) {
        return super.delete(id, SubjectRes.class);
    }
}
