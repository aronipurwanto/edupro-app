package org.edupro.web.student.service;

import org.edupro.web.exception.EduProWebException;
import org.edupro.web.student.model.StudentReq;
import org.edupro.web.student.model.StudentRes;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<StudentRes> get() throws EduProWebException;
    Optional<StudentRes> getById(String id) throws EduProWebException;;
    Optional<StudentRes> save(StudentReq request) throws EduProWebException;
    Optional<StudentRes> update(StudentReq request, String id) throws EduProWebException;;
    Optional<StudentRes> delete(String id) throws EduProWebException;;
}
