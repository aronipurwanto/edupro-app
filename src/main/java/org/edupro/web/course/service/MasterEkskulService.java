package org.edupro.web.course.service;

import org.edupro.web.course.model.EkskulRequest;
import org.edupro.web.base.EkskulResponse;

import java.util.List;
import java.util.Optional;

public interface MasterEkskulService {
    List<EkskulResponse> get();
    Optional<EkskulResponse> getById(Integer id);
    Optional<EkskulResponse> save(EkskulRequest request);
    Optional<EkskulResponse> update(EkskulRequest request, Integer id);
    Optional<EkskulResponse> delete(Integer id);
}
