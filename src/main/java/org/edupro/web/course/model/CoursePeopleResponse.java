package org.edupro.web.course.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoursePeopleResponse {
    private String courseId;
    private List<CoursePersonResponse> teachers;
    private List<CourseSiswaResponse> students;
}