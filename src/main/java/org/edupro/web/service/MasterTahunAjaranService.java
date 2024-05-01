package org.edupro.web.service;

import org.edupro.web.model.request.TahunAjaranRequest;
import org.edupro.web.model.response.TahunAjaranResponse;

import java.util.List;
import java.util.Optional;

public interface MasterTahunAjaranService {
    List<TahunAjaranResponse> get();
    List<TahunAjaranResponse> getByKurikulumId(String kurikulumId);
    Optional<TahunAjaranResponse> getById(String id);
    Optional<TahunAjaranResponse> save(TahunAjaranRequest request);
    Optional<TahunAjaranResponse> update(TahunAjaranRequest request, String id);
    Optional<TahunAjaranResponse> delete(String id);
}
