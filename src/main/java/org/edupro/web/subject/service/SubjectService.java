package org.edupro.web.subject.service;

import org.edupro.web.subject.model.SubjectReq;
import org.edupro.web.subject.model.SubjectRes;

import java.util.List;
import java.util.Optional;

public interface SubjectService {

    List<SubjectRes> get();
    Optional<SubjectRes> getById(String id);
    Optional<SubjectRes> save(SubjectReq request);
    Optional<SubjectRes> update(SubjectReq request, String id);
    Optional<SubjectRes> delete(String id);
}
