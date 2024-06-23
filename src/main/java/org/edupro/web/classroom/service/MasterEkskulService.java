package org.edupro.web.classroom.service;

import org.edupro.web.course.model.EkskulRequest;
import org.edupro.web.course.model.EkskulRes;

import java.util.List;
import java.util.Optional;

public interface MasterEkskulService {
    List<EkskulRes> get();
    Optional<EkskulRes> getById(Integer id);
    Optional<EkskulRes> save(EkskulRequest request);
    Optional<EkskulRes> update(EkskulRequest request, Integer id);
    Optional<EkskulRes> delete(Integer id);
}
