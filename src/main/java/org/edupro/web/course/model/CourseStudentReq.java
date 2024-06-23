package org.edupro.web.course.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseStudentReq {
    private String courseId;
    private String siswaId;

    public CourseStudentReq(String courseId) {
        this.courseId = courseId;
    }
}
