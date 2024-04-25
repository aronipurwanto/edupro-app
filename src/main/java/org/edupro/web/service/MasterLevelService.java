package org.edupro.web.service;

import org.edupro.web.model.request.LevelRequest;
import org.edupro.web.model.response.LevelResponse;

import java.util.List;
import java.util.Optional;

public interface MasterLevelService {
    List<LevelResponse> get();
    Optional<LevelResponse> getById(String id, String kode);
    Optional<LevelResponse> save(LevelRequest request);
    Optional<LevelResponse> update(LevelRequest request);
    Optional<LevelResponse> delete(LevelRequest request);
}
