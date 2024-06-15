package org.edupro.web.classroom.service;

import org.edupro.web.classroom.model.ExamReq;
import org.edupro.web.classroom.model.ExamRes;

import java.util.List;
import java.util.Optional;

public interface ExamService {
    List<ExamRes> getAll();
    Optional<ExamRes> getById(Integer id);
    Optional<ExamRes> save(ExamReq request);
    Optional<ExamRes> update(ExamReq request, Integer id);
    Optional<ExamRes> delete(Integer id);
}
