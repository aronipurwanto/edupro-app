package org.edupro.web.service.impl;

import lombok.RequiredArgsConstructor;
import org.edupro.web.model.request.BulanRequest;
import org.edupro.web.model.response.BulanResponse;
import org.edupro.web.service.MasterBulanService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MasterBulanServiceImpl implements MasterBulanService {
    private final RestTemplate restTemplate;

    private static List<BulanResponse> DATA = Arrays.asList(
            new BulanResponse(1,"Juni 2024","2023/2024","2023/2024 || 2024 || 06"),
            new BulanResponse(2,"Mei 2024","2023/2024","2023/2024 || 2024 || 05"),
            new BulanResponse(3,"April 2024","2023/2024","2023/2024 || 2024 || 04"),
            new BulanResponse(4,"Maret 2024","2023/2024","2023/2024 || 2024 || 03"),
            new BulanResponse(5,"Februari 2024","2023/2024","2023/2024 || 2024 || 02"),
            new BulanResponse(6,"Januari 2024","2023/2024","2023/2024 || 2024 || 01"),
            new BulanResponse(7,"Desemer 2023","2023/2024","2023/2024 || 2023 || 12"),
            new BulanResponse(8,"November 2023","2023/2024","2023/2024 || 2023 || 11"),
            new BulanResponse(9,"Oktober 2023","2023/2024","2023/2024 || 2023 || 10"),
            new BulanResponse(10,"September 2023","2023/2024","2023/2024 || 2023 || 09"),
            new BulanResponse(11,"Agustus 2023","2023/2024","2023/2024 || 2023 || 08"),
            new BulanResponse(12,"Juli 2023","2023/2024","2023/2024 || 2023 || 07")
    );
    @Override
    public List<BulanResponse> get() {
        return DATA;
    }

    @Override
    public Optional<BulanResponse> getById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Optional<BulanResponse> save(BulanRequest request) {
        return Optional.empty();
    }

    @Override
    public Optional<BulanResponse> update(BulanRequest request, Integer id) {
        return Optional.empty();
    }

    @Override
    public Optional<BulanResponse> delete(Integer id) {
        return Optional.empty();
    }
}
