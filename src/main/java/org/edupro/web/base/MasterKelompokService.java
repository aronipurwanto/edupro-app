package org.edupro.web.base;

import java.util.List;
import java.util.Optional;

public interface MasterKelompokService {
    List<KelompokResponse> get();

    Optional<KelompokResponse> getById(String id);

    Optional<KelompokResponse> save(KelompokRequest request);

    Optional<KelompokResponse> update(KelompokRequest request, String id);

    Optional<KelompokResponse> delete(String id);

}
