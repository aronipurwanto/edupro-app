package org.edupro.web.classroom.service;

import org.edupro.web.course.model.CourseStudentReq;
import org.edupro.web.course.model.CourseStudentRes;

import java.util.Optional;

public interface CourseStudentService {
    Optional<CourseStudentRes> saveSiswa(CourseStudentReq request);
}
