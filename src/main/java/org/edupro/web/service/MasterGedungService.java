package org.edupro.web.service;

import org.edupro.web.model.request.GedungRequest;
import org.edupro.web.model.response.GedungResponse;

import java.util.List;
import java.util.Optional;

public interface MasterGedungService {
    List<GedungResponse> get();
    Optional<GedungResponse> getById(String id);
    Optional<GedungResponse> save(GedungRequest request);
    Optional<GedungResponse> update(GedungRequest request, String id);
    Optional<GedungResponse> delete(String id);
}
