package org.edupro.web.service;

import org.edupro.web.exception.EduProWebException;
import org.edupro.web.model.request.SiswaRequest;
import org.edupro.web.model.response.SiswaResponse;

import java.util.List;
import java.util.Optional;

public interface MasterSiswaService {
    List<SiswaResponse> get() throws EduProWebException;
    Optional<SiswaResponse> getById(String id) throws EduProWebException;;
    Optional<SiswaResponse> save(SiswaRequest request) throws EduProWebException;
    Optional<SiswaResponse> update(SiswaRequest request, String id) throws EduProWebException;;
    Optional<SiswaResponse> delete(String id) throws EduProWebException;;
}
