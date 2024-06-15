package org.edupro.web.classroom.service;

import org.edupro.web.classroom.model.ClassScheduleReq;
import org.edupro.web.classroom.model.ClassScheduleRes;

import java.util.List;
import java.util.Optional;

public interface ClassScheduleService {
    List<ClassScheduleRes> get();
    Optional<ClassScheduleRes> add(ClassScheduleReq request);
}
