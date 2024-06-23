package org.edupro.web.level.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.edupro.web.base.service.BaseService;
import org.edupro.web.constant.BaseApiUrl;
import org.edupro.web.level.model.LevelReq;
import org.edupro.web.level.model.LevelRes;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class LevelServiceImpl extends BaseService<LevelRes, LevelReq> implements LevelService {
    public LevelServiceImpl(String backEndUrl, RestTemplate restTemplate, ObjectMapper objectMapper) {
        super(backEndUrl+ BaseApiUrl.LEVEL_URL, restTemplate, objectMapper);
    }

    @Override
    public List<LevelRes> get() {
        return super.get(LevelRes.class);
    }

    @Override
    public Optional<LevelRes> getById(String id) {
        return super.getById(id, LevelRes.class);
    }

    @Override
    public Optional<LevelRes> save(LevelReq request) {
        return super.save(request, LevelRes.class);
    }

    @Override
    public Optional<LevelRes> update(LevelReq request, String id) {
        return super.update(request, id, LevelRes.class);
    }

    @Override
    public Optional<LevelRes> delete(String id) {
        return super.delete(id, LevelRes.class);
    }
}