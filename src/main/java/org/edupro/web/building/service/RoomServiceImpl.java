package org.edupro.web.building.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.edupro.web.base.service.BaseService;
import org.edupro.web.building.model.RoomReq;
import org.edupro.web.building.model.RoomRes;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl extends BaseService<RoomRes, RoomReq> implements RoomService {

    public RoomServiceImpl(String backEndUrl, RestTemplate restTemplate, ObjectMapper objectMapper) {
        super(backEndUrl, restTemplate, objectMapper);
    }

    @Override
     public List<RoomRes> get() {
         return super.get(RoomRes.class);
    }

    @Override
    public Optional<RoomRes> getById(String id) {
        return super.getById(id, RoomRes.class);
    }

    @Override
    public Optional<RoomRes> save(RoomReq request) {
         return super.save(request, RoomRes.class);
    }

    @Override
    public Optional<RoomRes> update(RoomReq request, String id) {
        return super.update(request, id, RoomRes.class);
    }

    @Override
    public Optional<RoomRes> delete(String id) {
        return super.delete(id, RoomRes.class);
    }
}
