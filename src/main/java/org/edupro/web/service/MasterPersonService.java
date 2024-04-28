package org.edupro.web.service;

import org.edupro.web.model.request.PersonRequest;
import org.edupro.web.model.response.PersonResponse;

import java.util.List;
import java.util.Optional;

public interface MasterPersonService {
    List<PersonResponse> get();
    Optional<PersonResponse> getById(String id);
    Optional<PersonResponse> save(PersonRequest request);
    Optional<PersonResponse> update(PersonRequest request, String id);
    Optional<PersonResponse> delete(String id);
}
