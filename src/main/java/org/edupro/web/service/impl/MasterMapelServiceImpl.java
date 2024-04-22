package org.edupro.web.service.impl;

import lombok.RequiredArgsConstructor;
import org.edupro.web.model.request.MapelRequest;
import org.edupro.web.model.response.MapelResponse;
import org.edupro.web.service.MasterMapelService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MasterMapelServiceImpl implements MasterMapelService {

    private final RestTemplate restTemplate;
    private static List<MapelResponse> DATA = Arrays.asList(
            new MapelResponse(1,"-","Semua", "IX-AL-QURAN","AL-QURAN","IX",0,0.0,"Umum","Semua","Aktif"),
            new MapelResponse(2," ","Semua", "IX-B.Arab","Bahasa Arab","IX",0,0.0,"Islam","Semua","Aktif"),
            new MapelResponse(3,"-","Semua", "IX-B.Indonesia","Bahasa Indonesia","IX",0,0.0,"Umum","Semua","Aktif"),
            new MapelResponse(4," ","Semua", "IX-B.Inggris","Bahasa Inggris","IX",0,0.0,"Umum","Semua","Aktif"),
            new MapelResponse(5," ","Semua", "IX-IPA","IPA","IX",0,0.0,"Umum","Semua","Aktif"),
            new MapelResponse(6,"-","Semua", "IX-IPS","IPS","IX",0,0.0,"Umum","Semua","Aktif"),
            new MapelResponse(7," ","Semua", "IX-Mentoring","Mentoring","IX",0,0.0,"Umum","Semua","Aktif"),
            new MapelResponse(8," ","Semua", "IX-MTK","Matematika","IX",0,0.0,"Umum","Semua","Aktif"),
            new MapelResponse(9," ","Semua", "IX-MTK","Matematika","IX",0,0.0,"Umum","Semua","Aktif"),
            new MapelResponse(10," ","Semua", "IX-MTK","Matematika","IX",0,0.0,"Umum","Semua","Aktif")
    );

    public List<MapelResponse> get(){
        return DATA;
    }

    @Override
    public Optional<MapelResponse> getById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Optional<MapelResponse> update(MapelRequest request, Integer id) {
        return Optional.empty();
    }

    @Override
    public Optional<MapelResponse> delete(Integer id) {
        return Optional.empty();
    }

    @Override
    public Optional<MapelResponse> save(MapelRequest request) {
        if (request == null){
            return Optional.empty();
        }

        var data = convertResponse(request);
        DATA.add(data);
        return Optional.of(data);
    }

    private MapelResponse convertResponse (MapelRequest request){
        MapelResponse result = new MapelResponse();
        BeanUtils.copyProperties(request, result);
        return result;
    }

    private MapelRequest convertRequest(MapelResponse response){
        MapelRequest result = new MapelRequest();
        BeanUtils.copyProperties(response, result);
        return result;
    }
}
