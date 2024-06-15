package org.edupro.web.level.service;

import org.edupro.web.level.model.LevelReq;
import org.edupro.web.level.model.LevelRes;

import java.util.List;
import java.util.Optional;

public interface LevelService {
    List<LevelRes> get();
    Optional<LevelRes> getById(String id);
    Optional<LevelRes> save(LevelReq request);
    Optional<LevelRes> update(LevelReq request, String id);
    Optional<LevelRes> delete(String id);
}
