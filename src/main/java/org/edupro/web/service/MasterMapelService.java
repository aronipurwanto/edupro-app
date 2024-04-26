package org.edupro.web.service;

import org.edupro.web.model.request.MapelRequest;
import org.edupro.web.model.response.MapelResponse;

import java.util.List;
import java.util.Optional;

public interface MasterMapelService {

    List<MapelResponse> get();
    Optional<MapelResponse> getById(String id);
    Optional<MapelResponse> save(MapelRequest request);
    Optional<MapelResponse> update(MapelRequest request, String id);
    Optional<MapelResponse> delete(String id);
}
