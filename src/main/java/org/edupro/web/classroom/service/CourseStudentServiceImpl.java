package org.edupro.web.classroom.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.edupro.web.base.service.BaseService;
import org.edupro.web.course.model.CourseStudentReq;
import org.edupro.web.course.model.CourseStudentRes;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class CourseStudentServiceImpl extends BaseService<CourseStudentRes, CourseStudentReq> implements CourseStudentService {
    public CourseStudentServiceImpl(String backEndUrl, RestTemplate restTemplate, ObjectMapper objectMapper) {
        super(backEndUrl, restTemplate, objectMapper);
    }

    @Override
    public Optional<CourseStudentRes> saveSiswa(CourseStudentReq request) {
        return Optional.empty();
    }
}
