package org.edupro.web.service;

import org.edupro.web.model.request.CourseRequest;
import org.edupro.web.model.response.CourseResponse;

import java.util.List;
import java.util.Optional;

public interface MasterCourseService {
    List<CourseResponse> get();
    Optional<CourseResponse> getById(String id);
    Optional<CourseResponse> save(CourseRequest courseRequest);
    Optional<CourseResponse> update(CourseRequest courseRequest, String id);
    Optional<CourseResponse> delete(String id);
}
