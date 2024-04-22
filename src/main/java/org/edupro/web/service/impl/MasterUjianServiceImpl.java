package org.edupro.web.service.impl;

import lombok.RequiredArgsConstructor;
import org.edupro.web.model.request.UjianRequest;
import org.edupro.web.model.response.UjianResponse;
import org.edupro.web.service.MasterUjianService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class MasterUjianServiceImpl implements MasterUjianService {
    private final RestTemplate restTemplate;

    private static List<UjianResponse> DATA = Arrays.asList(
            new UjianResponse(1,"PAS/SAS-2023/2024","Penilaian Ahkir Semester 2023/2024","SAS-Ganjil-2023-2024 SAS-Genap-2023/2024","PAS-Ganjil-2023/2024 PAS-Ganjil-2023/2024", "Aktif","Aktif"),
            new UjianResponse(2,"PAS/SAS-2023/2024","Penilaian Ahkir Semester 2023/2024","SAS-Ganjil-2023-2024 SAS-Genap-2023/2024","PAS-Ganjil-2023/2024 PAS-Ganjil-2023/2024", "Aktif","Aktif"),
            new UjianResponse(3,"PAS/SAS-2023/2024","Penilaian Ahkir Semester 2023/2024","SAS-Ganjil-2023-2024 SAS-Genap-2023/2024","PAS-Ganjil-2023/2024 PAS-Ganjil-2023/2024", "Aktif","Aktif"),
            new UjianResponse(4,"PAS/SAS-2023/2024","Penilaian Ahkir Semester 2023/2024","SAS-Ganjil-2023-2024 SAS-Genap-2023/2024","PAS-Ganjil-2023/2024 PAS-Ganjil-2023/2024", "Aktif","Aktif"),
            new UjianResponse(5,"PAS/SAS-2023/2024","Penilaian Ahkir Semester 2023/2024","SAS-Ganjil-2023-2024 SAS-Genap-2023/2024","PAS-Ganjil-2023/2024 PAS-Ganjil-2023/2024", "Aktif","Aktif"),
            new UjianResponse(6,"PAS/SAS-2023/2024","Penilaian Ahkir Semester 2023/2024","SAS-Ganjil-2023-2024 SAS-Genap-2023/2024","PAS-Ganjil-2023/2024 PAS-Ganjil-2023/2024", "Aktif","Aktif"),
            new UjianResponse(7,"PAS/SAS-2023/2024","Penilaian Ahkir Semester 2023/2024","SAS-Ganjil-2023-2024 SAS-Genap-2023/2024","PAS-Ganjil-2023/2024 PAS-Ganjil-2023/2024", "Aktif","Aktif"),
            new UjianResponse(8,"PAS/SAS-2023/2024","Penilaian Ahkir Semester 2023/2024","SAS-Ganjil-2023-2024 SAS-Genap-2023/2024","PAS-Ganjil-2023/2024 PAS-Ganjil-2023/2024", "Aktif","Aktif")
    );
    @Override
    public List<UjianResponse> getAll() {
        return DATA;
    }

    @Override
    public Optional<UjianResponse> getById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Optional<UjianResponse> save(UjianRequest request) {
        if (request == null){
            return Optional.empty();
        }
        var data = convertResponse(request);
        DATA.add(data);
        return Optional.of(data);
    }

    @Override
    public Optional<UjianResponse> update(UjianRequest request, Integer id) {
        return Optional.empty();
    }

    @Override
    public Optional<UjianResponse> delete(Integer id) {
        return Optional.empty();
    }

    private UjianResponse convertResponse(UjianRequest request) {
        UjianResponse result  = new UjianResponse();
        BeanUtils.copyProperties(request, result);
        return result;
    }

    private UjianRequest convertRequest(UjianResponse response){
        UjianRequest result = new UjianRequest();
        BeanUtils.copyProperties(response, result);
        return result;
    }
}
