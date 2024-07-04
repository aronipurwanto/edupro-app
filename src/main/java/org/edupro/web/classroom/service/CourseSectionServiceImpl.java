package org.edupro.web.classroom.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.edupro.web.base.service.BaseService;
import org.edupro.web.constant.BaseApiUrl;
import org.edupro.web.course.model.CourseSectionReq;
import org.edupro.web.course.model.CourseSectionRes;
import org.edupro.web.exception.EduProWebException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class CourseSectionServiceImpl extends BaseService<CourseSectionRes, CourseSectionReq> implements CourseSectionService {
    public CourseSectionServiceImpl(String backEndUrl, RestTemplate restTemplate, ObjectMapper objectMapper) {
        super(backEndUrl+ BaseApiUrl.SECTION_URL, restTemplate, objectMapper);
    }

    @Override
    public List<CourseSectionRes> getTopicByCourseId(String courseId) throws EduProWebException {
        String url = "/"+courseId+"/topic";
        return super.getByCustomUrl(url, CourseSectionRes.class);
    }

    @Override
    public List<CourseSectionRes> getSectionByCourseId(String courseId) throws EduProWebException {
        String url = "/"+courseId;
        return super.getByCustomUrl(url, CourseSectionRes.class);
    }

    @Override
    public Optional<CourseSectionRes> saveSection(CourseSectionReq request) throws EduProWebException {
        return super.save(request, CourseSectionRes.class);
    }
}
