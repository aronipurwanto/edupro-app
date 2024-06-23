package org.edupro.web.building.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.edupro.web.base.service.BaseService;
import org.edupro.web.building.model.BuildingReq;
import org.edupro.web.building.model.BuildingRes;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class BuildingServiceImpl extends BaseService<BuildingRes, BuildingReq> implements BuildingService {
    public BuildingServiceImpl(String backEndUrl, RestTemplate restTemplate, ObjectMapper objectMapper) {
        super(backEndUrl, restTemplate, objectMapper);
    }

    @Override
    public List<BuildingRes> get() {
        return super.get(BuildingRes.class);
    }

    @Override
    public Optional<BuildingRes> getById(String id) {
        return super.getById(id, BuildingRes.class);
    }

    @Override
    public Optional<BuildingRes> save(BuildingReq request) {
        return super.save(request, BuildingRes.class);
    }


    @Override
    public Optional<BuildingRes> update(BuildingReq request, String id) {
        return super.update(request, id, BuildingRes.class);
    }

    @Override
    public Optional<BuildingRes> delete(String id) {
        return super.delete(id, BuildingRes.class);
    }
}
