package org.edupro.web.classroom.service;

import org.edupro.web.course.model.CoursePeopleRes;

import java.util.Optional;

public interface CoursePeopleService {
    Optional<CoursePeopleRes> getPeople(String id);
}
