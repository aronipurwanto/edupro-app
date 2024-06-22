package org.edupro.web.course.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoursePersonRes {
    private String courseId;
    private String personId;
    private String personName;
}
