package org.edupro.web.building.service;

import org.edupro.web.building.model.RoomReq;
import org.edupro.web.building.model.RoomRes;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    List<RoomRes> get();
    Optional<RoomRes> getById(String id);
    Optional<RoomRes> save(RoomReq request);
    Optional<RoomRes> update(RoomReq request, String id);
    Optional<RoomRes> delete(String id);
}
