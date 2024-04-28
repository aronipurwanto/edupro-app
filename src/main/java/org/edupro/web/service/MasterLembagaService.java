package org.edupro.web.service;

import org.edupro.web.model.request.LembagaRequest;
import org.edupro.web.model.response.LembagaResponse;

import java.util.List;
import java.util.Optional;

public interface MasterLembagaService {
    List<LembagaResponse> get();
    Optional<LembagaResponse> getById(String id);
    Optional<LembagaResponse> save(LembagaRequest request);
    Optional<LembagaResponse> update(LembagaRequest request, String id);
    Optional<LembagaResponse> delete(String id);
}
