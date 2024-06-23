package org.edupro.web.person.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.edupro.web.base.service.BaseService;
import org.edupro.web.constant.EduProApiUrl;
import org.edupro.web.person.model.PersonReq;
import org.edupro.web.person.model.PersonRes;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl extends BaseService<PersonRes,PersonReq> implements PersonService {
    public PersonServiceImpl(String backEndUrl, RestTemplate restTemplate, ObjectMapper objectMapper) {
        super(backEndUrl+EduProApiUrl.PERSON_URL, restTemplate, objectMapper);
    }

    @Override
    public List<PersonRes> get() {
        return super.get(PersonRes.class);
    }

    @Override
    public Optional<PersonRes> getById(String id) {
        return super.getById(id, PersonRes.class);
    }

    @Override
    public Optional<PersonRes> save(PersonReq request) {
        return super.save(request, PersonRes.class);
    }

    @Override
    public Optional<PersonRes> update(PersonReq request, String id) {
        return super.update(request, id, PersonRes.class);
    }

    @Override
    public Optional<PersonRes> delete(String id) {
        return super.delete(id, PersonRes.class);
    }
}
