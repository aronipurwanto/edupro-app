package org.edupro.web.service;

import org.edupro.web.model.request.LookupRequest;
import org.edupro.web.model.response.CommonResponse;
import org.edupro.web.model.response.LookupResponse;

import java.util.List;
import java.util.Optional;

public interface MasterLookupService {
    List<LookupResponse> get();
    List<LookupResponse> getByGroup(String group);
    List<CommonResponse> getGroup();
    Optional<LookupResponse> getById(String id);
    Optional<LookupResponse> save(LookupRequest request);
    Optional<LookupResponse> update(LookupRequest request, String id);
    Optional<LookupResponse> delete(String id);
}
