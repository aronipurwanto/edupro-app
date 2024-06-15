package org.edupro.web.curriculum.service;

import org.edupro.web.curriculum.model.CurriculumReq;
import org.edupro.web.curriculum.model.CurriculumRes;

import java.util.List;
import java.util.Optional;

public interface CurriculumService {
    List<CurriculumRes> get();
    Optional<CurriculumRes> getById(String id);
    Optional<CurriculumRes> save(CurriculumReq request);
    Optional<CurriculumRes> update(CurriculumReq request, String id);
    Optional<CurriculumRes> delete(String id);
}
