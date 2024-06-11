package org.edupro.web.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoursePersonRequest {
    private String courseId;
    private String personId;

    public CoursePersonRequest(String courseId) {
        this.courseId = courseId;
    }
}
