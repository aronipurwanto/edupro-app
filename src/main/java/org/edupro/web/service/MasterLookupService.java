package org.edupro.web.service;

import org.edupro.web.model.request.LookupRequest;
import org.edupro.web.model.response.CommonResponse;
import org.edupro.web.model.response.LookupResponse;

import java.util.List;
import java.util.Optional;

public interface MasterLookupService {
    List<LookupResponse> get();
    List<CommonResponse> getGroup();
    Optional<LookupResponse> getById(Integer id);
    Optional<LookupResponse> save(LookupRequest request);
    Optional<LookupResponse> update(LookupRequest request);
    Optional<LookupResponse> delete(Integer id);
}
