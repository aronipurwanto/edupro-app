package org.edupro.web.classroom.service;

import lombok.RequiredArgsConstructor;
import org.edupro.web.classroom.model.ExamReq;
import org.edupro.web.classroom.model.ExamRes;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {
    private final RestTemplate restTemplate;

    private static List<ExamRes> DATA = Arrays.asList(
            new ExamRes(1,"PAS/SAS-2023/2024","Penilaian Ahkir Semester 2023/2024","SAS-Ganjil-2023-2024 SAS-Genap-2023/2024","PAS-Ganjil-2023/2024 PAS-Ganjil-2023/2024", "Aktif","Aktif"),
            new ExamRes(2,"PAS/SAS-2023/2024","Penilaian Ahkir Semester 2023/2024","SAS-Ganjil-2023-2024 SAS-Genap-2023/2024","PAS-Ganjil-2023/2024 PAS-Ganjil-2023/2024", "Aktif","Aktif"),
            new ExamRes(3,"PAS/SAS-2023/2024","Penilaian Ahkir Semester 2023/2024","SAS-Ganjil-2023-2024 SAS-Genap-2023/2024","PAS-Ganjil-2023/2024 PAS-Ganjil-2023/2024", "Aktif","Aktif"),
            new ExamRes(4,"PAS/SAS-2023/2024","Penilaian Ahkir Semester 2023/2024","SAS-Ganjil-2023-2024 SAS-Genap-2023/2024","PAS-Ganjil-2023/2024 PAS-Ganjil-2023/2024", "Aktif","Aktif"),
            new ExamRes(5,"PAS/SAS-2023/2024","Penilaian Ahkir Semester 2023/2024","SAS-Ganjil-2023-2024 SAS-Genap-2023/2024","PAS-Ganjil-2023/2024 PAS-Ganjil-2023/2024", "Aktif","Aktif"),
            new ExamRes(6,"PAS/SAS-2023/2024","Penilaian Ahkir Semester 2023/2024","SAS-Ganjil-2023-2024 SAS-Genap-2023/2024","PAS-Ganjil-2023/2024 PAS-Ganjil-2023/2024", "Aktif","Aktif"),
            new ExamRes(7,"PAS/SAS-2023/2024","Penilaian Ahkir Semester 2023/2024","SAS-Ganjil-2023-2024 SAS-Genap-2023/2024","PAS-Ganjil-2023/2024 PAS-Ganjil-2023/2024", "Aktif","Aktif"),
            new ExamRes(8,"PAS/SAS-2023/2024","Penilaian Ahkir Semester 2023/2024","SAS-Ganjil-2023-2024 SAS-Genap-2023/2024","PAS-Ganjil-2023/2024 PAS-Ganjil-2023/2024", "Aktif","Aktif")
    );
    @Override
    public List<ExamRes> getAll() {
        return DATA;
    }

    @Override
    public Optional<ExamRes> getById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Optional<ExamRes> save(ExamReq request) {
        if (request == null){
            return Optional.empty();
        }
        var data = convertResponse(request);
        DATA.add(data);
        return Optional.of(data);
    }

    @Override
    public Optional<ExamRes> update(ExamReq request, Integer id) {
        return Optional.empty();
    }

    @Override
    public Optional<ExamRes> delete(Integer id) {
        return Optional.empty();
    }

    private ExamRes convertResponse(ExamReq request) {
        ExamRes result  = new ExamRes();
        BeanUtils.copyProperties(request, result);
        return result;
    }

    private ExamReq convertRequest(ExamRes response){
        ExamReq result = new ExamReq();
        BeanUtils.copyProperties(response, result);
        return result;
    }
}
