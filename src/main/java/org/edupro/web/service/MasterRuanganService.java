package org.edupro.web.service;

import org.edupro.web.model.request.RuanganRequest;
import org.edupro.web.model.response.RuanganResponse;

import java.util.List;
import java.util.Optional;

public interface MasterRuanganService {
    List<RuanganResponse> get();
    Optional<RuanganResponse> getById(String id);
    Optional<RuanganResponse> save(RuanganRequest request);
    Optional<RuanganResponse> update(RuanganRequest request, String id);
    Optional<RuanganResponse> delete(String id);
}
