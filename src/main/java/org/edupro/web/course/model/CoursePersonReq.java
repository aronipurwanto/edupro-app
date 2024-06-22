package org.edupro.web.course.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoursePersonReq {
    private String courseId;
    private String personId;

    public CoursePersonReq(String courseId) {
        this.courseId = courseId;
    }
}
