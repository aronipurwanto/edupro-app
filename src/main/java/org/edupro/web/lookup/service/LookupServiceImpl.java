package org.edupro.web.lookup.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.edupro.web.base.service.BaseService;
import org.edupro.web.lookup.model.LookupReq;
import org.edupro.web.lookup.model.LookupRes;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class LookupServiceImpl extends BaseService<LookupRes, LookupReq> implements LookupService {

    public LookupServiceImpl(String backEndUrl, RestTemplate restTemplate, ObjectMapper objectMapper) {
        super(backEndUrl, restTemplate, objectMapper);
    }

    @Override
    public List<LookupRes> get() {
        return super.get(LookupRes.class);
    }

    @Override
    public List<LookupRes> getByGroup(String group) {
        return super.getByGroup(group, LookupRes.class);
    }

    @Override
    public List<LookupRes> getGroup() {
        return super.getGroup(LookupRes.class);
    }

    @Override
    public Optional<LookupRes> getById(String id) {
        return super.getById(id, LookupRes.class);
    }

    @Override
    public Optional<LookupRes> save(LookupReq request) {
        return super.save(request, LookupRes.class);
    }

    @Override
    public Optional<LookupRes> update(LookupReq request, String id) {
        return super.update(request, id, LookupRes.class);
    }

    @Override
    public Optional<LookupRes> delete(String id) {
        return super.delete(id, LookupRes.class);
    }
}
