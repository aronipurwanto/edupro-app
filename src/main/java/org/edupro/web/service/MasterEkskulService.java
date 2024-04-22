package org.edupro.web.service;

import org.edupro.web.model.request.EkskulRequest;
import org.edupro.web.model.request.LevelRequest;
import org.edupro.web.model.response.EkskulResponse;
import org.edupro.web.model.response.LevelResponse;

import java.util.List;
import java.util.Optional;

public interface MasterEkskulService {
    List<EkskulResponse> get();
    Optional<EkskulResponse> getById(Integer id);
    Optional<EkskulResponse> save(EkskulRequest request);
    Optional<EkskulResponse> update(EkskulRequest request, Integer id);
    Optional<EkskulResponse> delete(Integer id);
}
