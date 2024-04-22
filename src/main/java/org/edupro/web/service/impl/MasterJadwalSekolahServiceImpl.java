package org.edupro.web.service.impl;

import com.fasterxml.jackson.databind.util.BeanUtil;
import lombok.RequiredArgsConstructor;

import org.edupro.web.model.request.JadwalSekolahRequest;
import org.edupro.web.model.request.LevelRequest;
import org.edupro.web.model.response.JadwalSekolahResponse;
import org.edupro.web.model.response.LevelResponse;
import org.edupro.web.service.MasterJadwalSekolahService;
import org.edupro.web.service.MasterLevelService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MasterJadwalSekolahServiceImpl implements MasterJadwalSekolahService {
    private final RestTemplate restTemplate;
    private static List<JadwalSekolahResponse> DATA= Arrays.asList(
            new JadwalSekolahResponse(1,"2023/2024","KBM","TP","Aktif","Total","28 Juli 2023", "IX,VII,VIII","Aktif"),
            new JadwalSekolahResponse(2,"2024/2025","KBM","TP","Aktif","Total","20 Juli 2024", "V","Aktif")
    );

    @Override
    public List<JadwalSekolahResponse> get() {
        return DATA;
    }

    @Override
    public Optional<JadwalSekolahResponse> add(JadwalSekolahRequest request) {
        if(request == null) {
            return Optional.empty();
        }

        var data = convertResponse(request);
        DATA.add(data);
        return Optional.of(data);
    }

    private JadwalSekolahResponse convertResponse(JadwalSekolahRequest request){
        JadwalSekolahResponse result = new JadwalSekolahResponse();
        BeanUtils.copyProperties(request, result);
        return result;
    }

    private JadwalSekolahRequest convertRequest(JadwalSekolahResponse response){
        JadwalSekolahRequest result = new JadwalSekolahRequest();
        BeanUtils.copyProperties(response, result);
        return result;
    }
}
