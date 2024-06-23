package org.edupro.web.course.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseStudentRes {
    private String courseId;
    private String siswaId;
    private String siswaName;
}
