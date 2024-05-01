package org.edupro.web.service.impl;

import lombok.RequiredArgsConstructor;
import org.edupro.web.model.request.EkskulRequest;
import org.edupro.web.model.response.EkskulResponse;
import org.edupro.web.service.MasterEkskulService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MasterEkskulServiceImpl extends BaseService implements MasterEkskulService {

    private final RestTemplate  restTemplate;

    private static List<EkskulResponse> DATA = Arrays.asList(
            new EkskulResponse(1,"Panahan","Panahan","Aktif"),
            new EkskulResponse(2,"Pramuka","Pramuka","Aktif"),
            new EkskulResponse(3,"Renang","Renang","Aktif"),
            new EkskulResponse(4,"Science","Science","Aktif"),
            new EkskulResponse(5,"Seni","Seni","Aktif"),
            new EkskulResponse(6,"Taekwondo","Taekwondo","Aktif")
    );

    public List<EkskulResponse> get(){
        return DATA;
    }

    @Override
    public Optional<EkskulResponse> getById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Optional<EkskulResponse> save(EkskulRequest request) {
        if (request == null){
            return Optional.empty();
        }

        var data = convertResponse(request);
        DATA.add(data);
        return Optional.of(data);
    }

    @Override
    public Optional<EkskulResponse> update(EkskulRequest request, Integer id) {
        return Optional.empty();
    }

    @Override
    public Optional<EkskulResponse> delete(Integer id) {
        return Optional.empty();
    }

    private EkskulResponse convertResponse(EkskulRequest request){
        EkskulResponse result = new EkskulResponse();
        BeanUtils.copyProperties(request, this);
        return result;
    }

    private EkskulRequest convertRequest(EkskulResponse response){
        EkskulRequest result = new EkskulRequest();
        BeanUtils.copyProperties(response, result);
        return result;
    }
}
