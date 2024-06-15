package org.edupro.web.course.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseSiswaRequest {
    private String courseId;
    private String siswaId;

    public CourseSiswaRequest(String courseId) {
        this.courseId = courseId;
    }
}
