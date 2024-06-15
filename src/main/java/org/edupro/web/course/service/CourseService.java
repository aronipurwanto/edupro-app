package org.edupro.web.course.service;

import org.edupro.web.exception.EduProWebException;
import org.edupro.web.course.model.CourseRequest;
import org.edupro.web.course.model.CourseSectionReq;
import org.edupro.web.course.model.CourseSiswaRequest;
import org.edupro.web.course.model.CoursePeopleResponse;
import org.edupro.web.course.model.CourseResponse;
import org.edupro.web.course.model.CourseSectionRes;
import org.edupro.web.course.model.CourseSiswaResponse;

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
