package org.edupro.web.course.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.edupro.web.base.service.BaseService;
import org.edupro.web.constant.BaseApiUrl;
import org.edupro.web.course.model.CourseReq;
import org.edupro.web.course.model.CourseRes;
import org.edupro.web.exception.EduProWebException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CourseServiceImpl extends BaseService<CourseRes, CourseReq> implements CourseService {

    public CourseServiceImpl(String backEndUrl, RestTemplate restTemplate, ObjectMapper objectMapper) {
        super(backEndUrl+ BaseApiUrl.COURSE_URL, restTemplate, objectMapper);
    }

    @Override
    public List<CourseRes> get() throws EduProWebException {
        return super.get(CourseRes.class);
    }

    @Override
    public List<CourseRes> getByUser() throws EduProWebException {
        return super.getByCustomUrl("/user", CourseRes.class);
    }

    @Override
    public Optional<CourseRes> getById(String id) throws EduProWebException{
        return super.getById(id, CourseRes.class);
    }

    @Override
    public Optional<CourseRes> save(CourseReq request) {
        return super.save(request, CourseRes.class);
    }

    @Override
    public Optional<CourseRes> update(CourseReq courseReq, String id) {
        return super.update(courseReq,id, CourseRes.class);
    }

    @Override
    public Optional<CourseRes> delete(String id) {
        return super.delete(id, CourseRes.class);
    }
}
