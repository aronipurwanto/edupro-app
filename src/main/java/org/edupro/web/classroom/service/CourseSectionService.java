package org.edupro.web.classroom.service;

import org.edupro.web.course.model.CourseSectionReq;
import org.edupro.web.course.model.CourseSectionRes;
import org.edupro.web.exception.EduProWebException;

import java.util.List;
import java.util.Optional;

public interface CourseSectionService {
    List<CourseSectionRes> getTopicByCourseId(String courseId) throws EduProWebException;
    List<CourseSectionRes> getSectionByCourseId(String courseId) throws EduProWebException;
    Optional<CourseSectionRes> saveSection(CourseSectionReq request) throws EduProWebException;
}
