package org.edupro.web.classroom.service;

import lombok.RequiredArgsConstructor;

import org.edupro.web.classroom.model.ClassScheduleReq;
import org.edupro.web.classroom.model.ClassScheduleRes;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClassScheduleServiceImpl implements ClassScheduleService {
    private final RestTemplate restTemplate;
    private static List<ClassScheduleRes> DATA= Arrays.asList(
            new ClassScheduleRes(1,"2023/2024","KBM","TP","Aktif","Total","28 Juli 2023", "IX,VII,VIII","Aktif"),
            new ClassScheduleRes(2,"2024/2025","KBM","TP","Aktif","Total","20 Juli 2024", "V","Aktif")
    );

    @Override
    public List<ClassScheduleRes> get() {
        return DATA;
    }

    @Override
    public Optional<ClassScheduleRes> add(ClassScheduleReq request) {
        if(request == null) {
            return Optional.empty();
        }

        var data = convertResponse(request);
        DATA.add(data);
        return Optional.of(data);
    }

    private ClassScheduleRes convertResponse(ClassScheduleReq request){
        ClassScheduleRes result = new ClassScheduleRes();
        BeanUtils.copyProperties(request, result);
        return result;
    }

    private ClassScheduleReq convertRequest(ClassScheduleRes response){
        ClassScheduleReq result = new ClassScheduleReq();
        BeanUtils.copyProperties(response, result);
        return result;
    }
}
