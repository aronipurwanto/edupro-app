package org.edupro.web.building.service;

import org.edupro.web.building.model.BuildingReq;
import org.edupro.web.building.model.BuildingRes;

import java.util.List;
import java.util.Optional;

public interface BuildingService {
    List<BuildingRes> get();

    Optional<BuildingRes> getById(String id);

    Optional<BuildingRes> save(BuildingReq request);

    Optional<BuildingRes> update(BuildingReq request, String id);

    Optional<BuildingRes> delete(String id);
}
