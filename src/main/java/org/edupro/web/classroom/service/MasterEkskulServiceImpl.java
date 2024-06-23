package org.edupro.web.classroom.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.edupro.web.base.service.BaseService;
import org.edupro.web.course.model.EkskulRequest;
import org.edupro.web.course.model.EkskulRes;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class MasterEkskulServiceImpl extends BaseService<EkskulRes, Object> implements MasterEkskulService {
    public MasterEkskulServiceImpl(String backEndUrl, RestTemplate restTemplate, ObjectMapper objectMapper) {
        super(backEndUrl, restTemplate, objectMapper);
    }

    public List<EkskulRes> get(){
        return super.get(EkskulRes.class);
    }

    @Override
    public Optional<EkskulRes> getById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Optional<EkskulRes> save(EkskulRequest request) {
        return Optional.empty();
    }

    @Override
    public Optional<EkskulRes> update(EkskulRequest request, Integer id) {
        return Optional.empty();
    }

    @Override
    public Optional<EkskulRes> delete(Integer id) {
        return Optional.empty();
    }

    private EkskulRes convertResponse(EkskulRequest request){
        EkskulRes result = new EkskulRes();
        BeanUtils.copyProperties(request, this);
        return result;
    }

    private EkskulRequest convertRequest(EkskulRes response){
        EkskulRequest result = new EkskulRequest();
        BeanUtils.copyProperties(response, result);
        return result;
    }
}
