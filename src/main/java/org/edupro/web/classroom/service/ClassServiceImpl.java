package org.edupro.web.classroom.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.edupro.web.base.service.BaseService;
import org.edupro.web.classroom.model.ClassReq;
import org.edupro.web.classroom.model.ClassRes;
import org.edupro.web.constant.BaseApiUrl;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ClassServiceImpl extends BaseService<ClassRes,ClassReq> implements ClassService {

    public ClassServiceImpl(String backEndUrl, RestTemplate restTemplate, ObjectMapper objectMapper) {
        super(backEndUrl+ BaseApiUrl.CLASS_URL, restTemplate, objectMapper);
    }

    @Override
    public List<ClassRes> get() {
        return super.get(ClassRes.class);
    }

    @Override
    public Optional<ClassRes> getById(String id) {
        return super.getById(id, ClassRes.class);
    }

    @Override
    public Optional<ClassRes> save(ClassReq request) {
        return super.save(request, ClassRes.class);
    }

    @Override
    public Optional<ClassRes> update(ClassReq request, String id) {
        return super.update(request, id, ClassRes.class);
    }

    @Override
    public Optional<ClassRes> delete(String id) {
        return super.delete(id, ClassRes.class);
    }
}
