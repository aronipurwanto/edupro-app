package org.edupro.web.classroom.service;

import org.edupro.web.classroom.model.ClassReq;
import org.edupro.web.classroom.model.ClassRes;

import java.util.List;
import java.util.Optional;

public interface ClassService {
    List<ClassRes> get();
    Optional<ClassRes> getById(String id);
    Optional<ClassRes> save(ClassReq request);
    Optional<ClassRes> update(ClassReq request, String id);
    Optional<ClassRes> delete(String id);
}
