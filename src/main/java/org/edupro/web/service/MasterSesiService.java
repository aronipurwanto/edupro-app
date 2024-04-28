package org.edupro.web.service;

import org.edupro.web.model.request.SesiRequest;
import org.edupro.web.model.response.SesiResponse;

import java.util.List;
import java.util.Optional;

public interface MasterSesiService {
    List<SesiResponse> get();
    Optional<SesiResponse> getById(String id);
    Optional<SesiResponse> save(SesiRequest request);
    Optional<SesiResponse> update(SesiRequest request, String id);
    Optional<SesiResponse> delete(String id);
}
