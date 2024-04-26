package org.edupro.web.service;

import org.edupro.web.model.request.SiswaRequest;
import org.edupro.web.model.response.SiswaResponse;

import java.util.List;
import java.util.Optional;

public interface MasterSiswaService {
    List<SiswaResponse> get();
    Optional<SiswaResponse> getById(String id);
    Optional<SiswaResponse> save(SiswaRequest request);
    Optional<SiswaResponse> update(SiswaRequest request);
    Optional<SiswaResponse> delete(SiswaRequest request);
}
