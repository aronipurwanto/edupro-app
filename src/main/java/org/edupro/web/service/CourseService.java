package org.edupro.web.service;

import org.edupro.web.exception.EduProWebException;
import org.edupro.web.model.request.CourseRequest;
import org.edupro.web.model.response.CourseResponse;
import org.edupro.web.model.response.CourseSectionRes;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<CourseResponse> get() throws EduProWebException;
    List<CourseResponse> getByUser() throws EduProWebException;
    List<CourseSectionRes> getAllSection() throws EduProWebException;
    List<CourseSectionRes> getSectionByCourseId(String courseId) throws EduProWebException;
    Optional<CourseResponse> getById(String id) throws EduProWebException;
    Optional<CourseResponse> save(CourseRequest courseRequest);
    Optional<CourseResponse> update(CourseRequest courseRequest, String id);
    Optional<CourseResponse> delete(String id);
}
