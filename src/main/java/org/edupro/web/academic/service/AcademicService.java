package org.edupro.web.academic.service;

import org.edupro.web.academic.model.AcademicYearReq;
import org.edupro.web.academic.model.AcademicYearRes;

import java.util.List;
import java.util.Optional;

public interface AcademicService {
    List<AcademicYearRes> get();
    List<AcademicYearRes> getByKurikulumId(String kurikulumId);
    Optional<AcademicYearRes> getById(String id);
    Optional<AcademicYearRes> save(AcademicYearReq request);
    Optional<AcademicYearRes> update(AcademicYearReq request, String id);
    Optional<AcademicYearRes> delete(String id);
}
