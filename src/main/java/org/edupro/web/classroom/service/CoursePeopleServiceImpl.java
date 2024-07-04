package org.edupro.web.classroom.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.edupro.web.base.service.BaseService;
import org.edupro.web.constant.BaseApiUrl;
import org.edupro.web.course.model.CoursePeopleRes;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.Optional;

@Service
public class CoursePeopleServiceImpl extends BaseService<CoursePeopleRes, Objects> implements CoursePeopleService {
    public CoursePeopleServiceImpl(String backEndUrl, RestTemplate restTemplate, ObjectMapper objectMapper) {
        super(backEndUrl+ BaseApiUrl.COURSE_URL, restTemplate, objectMapper);
    }

    @Override
    public Optional<CoursePeopleRes> getPeople(String id) {
        return super.getById(id+"/people", CoursePeopleRes.class);
    }
}
