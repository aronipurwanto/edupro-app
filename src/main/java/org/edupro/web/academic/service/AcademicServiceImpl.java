package org.edupro.web.academic.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.edupro.web.academic.model.AcademicYearReq;
import org.edupro.web.academic.model.AcademicYearRes;
import org.edupro.web.base.service.BaseService;
import org.edupro.web.constant.BaseApiUrl;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class AcademicServiceImpl extends BaseService<AcademicYearRes,AcademicYearReq> implements AcademicService {

    public AcademicServiceImpl(String backEndUrl, RestTemplate restTemplate, ObjectMapper objectMapper) {
        super(backEndUrl+ BaseApiUrl.ACADEMIC_YEAR_URL, restTemplate, objectMapper);
    }

    @Override
    public List<AcademicYearRes> get() {
        return super.get(AcademicYearRes.class);
    }

    @Override
    public List<AcademicYearRes> getByKurikulumId(String kurikulumId) {
        return super.getByCustomUrl("/"+kurikulumId, AcademicYearRes.class);
    }

    @Override
    public Optional<AcademicYearRes> getById(String id) {
        return super.getById(id, AcademicYearRes.class);
    }

    @Override
    public Optional<AcademicYearRes> save(AcademicYearReq request) {
       return super.save(request, AcademicYearRes.class);
    }

    @Override
    public Optional<AcademicYearRes> update(AcademicYearReq request, String id) {
        return super.update(request, id, AcademicYearRes.class);
    }

    @Override
    public Optional<AcademicYearRes> delete(String id) {
        return super.delete(id, AcademicYearRes.class);
    }
}
