package org.edupro.web.service;

import org.edupro.web.model.request.MapelRequest;
import org.edupro.web.model.response.MapelResponse;

import java.util.List;
import java.util.Optional;

public interface MasterMapelService {

    List<MapelResponse> get();
    Optional<MapelResponse> getById(Integer id);
    Optional<MapelResponse> save(MapelRequest request);
    Optional<MapelResponse> update(MapelRequest request, Integer id);
    Optional<MapelResponse> delete(Integer id);
}
