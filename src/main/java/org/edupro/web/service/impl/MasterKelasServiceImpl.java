package org.edupro.web.service.impl;

import lombok.RequiredArgsConstructor;
import org.edupro.web.model.request.KelasRequest;
import org.edupro.web.model.request.LevelRequest;
import org.edupro.web.model.response.KelasResponse;
import org.edupro.web.model.response.LevelResponse;
import org.edupro.web.service.MasterKelasService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MasterKelasServiceImpl implements MasterKelasService {
    private final RestTemplate restTemplate;
        private static List<KelasResponse> DATA = Arrays.asList(
                new KelasResponse(1,"Bhekty Rizky Ansyah","VII-A","VII","VII-A",12,"Aktif"),
                new KelasResponse(2,"Dyah Zakiati","VII-B","VII","VII-B",13,"Aktif"),
                new KelasResponse(3,"Qori Damaranti","VII","VII","VII",11,"Aktif"),
                new KelasResponse(4,"Nirmala","IX","IX","IX",15,"Aktif"),
                new KelasResponse(5,"Ikhlas Ramadhani","XI","XI","XI",16,"Aktif"),
                new KelasResponse(6,"Ikhlas Ramadhani","XI","XI","XI",16,"Aktif"),
                new KelasResponse(7,"Ikhlas Ramadhani","XI","XI","XI",16,"Aktif"),
                new KelasResponse(8,"Ikhlas Ramadhani","XI","XI","XI",16,"Aktif"),
                new KelasResponse(9,"Ikhlas Ramadhani","XI","XI","XI",16,"Aktif"),
                new KelasResponse(10,"Ikhlas Ramadhani","XI","XI","XI",16,"Aktif")
        );

    @Override
    public List<KelasResponse> getAll() {
        return DATA;
    }

    @Override
    public Optional<LevelResponse> getById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Optional<KelasResponse> save(KelasRequest request) {
        if (request == null){
            return Optional.empty();
        }

        var data = convertResponse(request);
        DATA.add(data);
        return Optional.of(data);
    }

    @Override
    public Optional<KelasResponse> update(KelasRequest request, Integer id) {
        return Optional.empty();
    }

    @Override
    public Optional<KelasResponse> delete(Integer id) {
        return Optional.empty();
    }

    private KelasResponse convertResponse(KelasRequest request) {
        KelasResponse result  = new KelasResponse();
        BeanUtils.copyProperties(request, result);
        return result;
    }

    private KelasRequest convertRequest(KelasResponse response){
        KelasRequest result = new KelasRequest();
        BeanUtils.copyProperties(response, result);
        return result;
    }
}
