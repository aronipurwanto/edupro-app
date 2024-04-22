package org.edupro.web.service;

import org.edupro.web.model.request.UjianRequest;
import org.edupro.web.model.response.UjianResponse;

import java.util.List;
import java.util.Optional;

public interface MasterUjianService {
    List<UjianResponse> getAll();
    Optional<UjianResponse> getById(Integer id);
    Optional<UjianResponse> save(UjianRequest request);
    Optional<UjianResponse> update(UjianRequest request, Integer id);
    Optional<UjianResponse> delete(Integer id);
}
