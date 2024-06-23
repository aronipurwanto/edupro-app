package org.edupro.web.course.service;

import org.edupro.web.course.model.*;
import org.edupro.web.exception.EduProWebException;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<CourseRes> get() throws EduProWebException;
    List<CourseRes> getByUser() throws EduProWebException;
    Optional<CourseRes> getById(String id) throws EduProWebException;
    Optional<CourseRes> save(CourseReq courseReq);
    Optional<CourseRes> update(CourseReq courseReq, String id);
    Optional<CourseRes> delete(String id);
}
