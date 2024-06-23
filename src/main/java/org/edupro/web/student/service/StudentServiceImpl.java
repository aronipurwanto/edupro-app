package org.edupro.web.student.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.edupro.web.base.service.BaseService;
import org.edupro.web.exception.EduProWebException;
import org.edupro.web.student.model.StudentReq;
import org.edupro.web.student.model.StudentRes;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl extends BaseService<StudentRes, StudentReq> implements StudentService {
    public StudentServiceImpl(String backEndUrl, RestTemplate restTemplate, ObjectMapper objectMapper) {
        super(backEndUrl, restTemplate, objectMapper);
    }

    @Override
     public List<StudentRes> get() throws EduProWebException {
         return super.get(StudentRes.class);
    }

    @Override
    public Optional<StudentRes> getById(String id) throws EduProWebException{
         return super.getById(id, StudentRes.class);
    }

    @Override
    public Optional<StudentRes> save(StudentReq request) throws EduProWebException {
         return super.save(request, StudentRes.class);
    }

    @Override
    public Optional<StudentRes> update(StudentReq request, String id) {
        return super.update(request, id, StudentRes.class);
    }

    @Override
    public Optional<StudentRes> delete(String id) {
        return super.delete(id, StudentRes.class);
    }
}
