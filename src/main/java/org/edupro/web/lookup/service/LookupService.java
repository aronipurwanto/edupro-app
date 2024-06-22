package org.edupro.web.lookup.service;

import org.edupro.web.lookup.model.LookupReq;
import org.edupro.web.lookup.model.LookupRes;

import java.util.List;
import java.util.Optional;

public interface LookupService {
    List<LookupRes> get();
    List<LookupRes> getByGroup(String group);
    List<LookupRes> getGroup();
    Optional<LookupRes> getById(String id);
    Optional<LookupRes> save(LookupReq request);
    Optional<LookupRes> update(LookupReq request, String id);
    Optional<LookupRes> delete(String id);
}
