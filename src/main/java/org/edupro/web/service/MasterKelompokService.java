package org.edupro.web.service;

import org.edupro.web.model.request.KelompokRequest;
import org.edupro.web.model.response.KelompokResponse;

import java.util.List;
import java.util.Optional;

public interface MasterKelompokService {
    List<KelompokResponse> get();

    Optional<KelompokResponse> getById(Integer id, String kode);

    Optional<KelompokResponse> save(KelompokRequest request);

    Optional<KelompokResponse> update(KelompokRequest request);

    Optional<KelompokResponse> delete(KelompokRequest request);

}
