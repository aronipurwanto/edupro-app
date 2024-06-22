package org.edupro.web.course.service;

import org.edupro.web.exception.EduProWebException;
import org.edupro.web.course.model.CourseReq;
import org.edupro.web.course.model.CourseSectionReq;
import org.edupro.web.course.model.CourseSiswaRequest;
import org.edupro.web.course.model.CoursePeopleRes;
import org.edupro.web.course.model.CourseRes;
import org.edupro.web.course.model.CourseSectionRes;
import org.edupro.web.course.model.CourseSiswaResponse;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<CourseRes> get() throws EduProWebException;
    List<CourseRes> getByUser() throws EduProWebException;
    Optional<CourseRes> getById(String id) throws EduProWebException;
    Optional<CourseRes> save(CourseReq courseReq);
    Optional<CourseRes> update(CourseReq courseReq, String id);
    Optional<CourseRes> delete(String id);

    List<CourseSectionRes> getTopicByCourseId(String courseId) throws EduProWebException;
    List<CourseSectionRes> getSectionByCourseId(String courseId) throws EduProWebException;
    Optional<CourseSectionRes> saveSection(CourseSectionReq request) throws EduProWebException;
    Optional<CoursePeopleRes> getPeople(String id);
    Optional<CourseSiswaResponse> saveSiswa(CourseSiswaRequest request);
}
