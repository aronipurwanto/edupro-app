package org.edupro.web.service;

import org.edupro.web.model.request.JadwalSekolahRequest;
import org.edupro.web.model.response.JadwalSekolahResponse;

import java.util.List;
import java.util.Optional;

public interface MasterJadwalSekolahService {
    List<JadwalSekolahResponse> get();
    Optional<JadwalSekolahResponse> add(JadwalSekolahRequest request);
}
