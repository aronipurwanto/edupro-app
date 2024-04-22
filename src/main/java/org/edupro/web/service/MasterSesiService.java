package org.edupro.web.service;

import org.edupro.web.model.request.SesiRequest;
import org.edupro.web.model.response.SesiResponse;

import java.util.List;
import java.util.Optional;

public interface MasterSesiService {
    List<SesiResponse> get();
    Optional<SesiResponse> getById(Integer id);
    Optional<SesiResponse> save(SesiRequest request);
    Optional<SesiResponse> update(SesiRequest request, Integer id);
    Optional<SesiResponse> delete(Integer id);
}
