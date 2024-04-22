package org.edupro.web.service;

import org.edupro.web.model.request.BulanRequest;
import org.edupro.web.model.response.BulanResponse;

import java.util.List;
import java.util.Optional;

public interface MasterBulanService {
    List<BulanResponse> get();
    Optional<BulanResponse> getById(Integer id);
    Optional<BulanResponse> save(BulanRequest request);
    Optional<BulanResponse> update(BulanRequest request, Integer id);
    Optional<BulanResponse> delete(Integer id);
}
