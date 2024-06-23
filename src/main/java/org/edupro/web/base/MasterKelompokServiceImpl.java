package org.edupro.web.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.edupro.web.base.service.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MasterKelompokServiceImpl extends BaseService<Objects,Objects> implements MasterKelompokService {
    public MasterKelompokServiceImpl(String backEndUrl, RestTemplate restTemplate, ObjectMapper objectMapper) {
        super(backEndUrl, restTemplate, objectMapper);
    }

    @Override
    public List<KelompokResponse> get() {
        return Collections.emptyList();
    }

    @Override
    public Optional<KelompokResponse> getById(String id) {
        return Optional.empty();
    }

    @Override
    public Optional<KelompokResponse> save(KelompokRequest request) {
        return Optional.empty();
    }

    @Override
    public Optional<KelompokResponse> update(KelompokRequest request, String id) {
        return Optional.empty();
    }

    @Override
    public Optional<KelompokResponse> delete(String id) {
        return Optional.empty();
    }
}
