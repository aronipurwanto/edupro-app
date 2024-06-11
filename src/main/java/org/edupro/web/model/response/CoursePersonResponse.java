package org.edupro.web.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoursePersonResponse {
    private String courseId;
    private String personId;
    private String personName;
}
