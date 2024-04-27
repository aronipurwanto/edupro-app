package org.edupro.web.service;

import org.edupro.web.model.request.KurikulumRequest;
import org.edupro.web.model.response.KurikulumResponse;

import java.util.List;
import java.util.Optional;

public interface MasterKurikulumService {
    List<KurikulumResponse> get();
    Optional<KurikulumResponse> getById(String id);
    Optional<KurikulumResponse> save(KurikulumRequest request);
    Optional<KurikulumResponse> update(KurikulumRequest request, String id);
    Optional<KurikulumResponse> delete(String id);
}
