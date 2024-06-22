package org.edupro.web.course.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoursePeopleRes {
    private String courseId;
    private List<CoursePersonRes> teachers;
    private List<CourseSiswaResponse> students;
}
