package org.edupro.web.service;

import org.edupro.web.exception.EduProWebException;
import org.edupro.web.model.request.CourseRequest;
import org.edupro.web.model.request.CourseSectionReq;
import org.edupro.web.model.request.CourseSiswaRequest;
import org.edupro.web.model.response.CoursePeopleResponse;
import org.edupro.web.model.response.CourseResponse;
import org.edupro.web.model.response.CourseSectionRes;
import org.edupro.web.model.response.CourseSiswaResponse;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<CourseResponse> get() throws EduProWebException;
    List<CourseResponse> getByUser() throws EduProWebException;
    Optional<CourseResponse> getById(String id) throws EduProWebException;
    Optional<CourseResponse> save(CourseRequest courseRequest);
    Optional<CourseResponse> update(CourseRequest courseRequest, String id);
    Optional<CourseResponse> delete(String id);

    List<CourseSectionRes> getTopicByCourseId(String courseId) throws EduProWebException;
    List<CourseSectionRes> getSectionByCourseId(String courseId) throws EduProWebException;
    Optional<CourseSectionRes> saveSection(CourseSectionReq request) throws EduProWebException;
    Optional<CoursePeopleResponse> getPeople(String id);
    Optional<CourseSiswaResponse> saveSiswa(CourseSiswaRequest request);
}
