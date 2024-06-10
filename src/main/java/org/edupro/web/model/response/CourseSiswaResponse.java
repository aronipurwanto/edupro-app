package org.edupro.web.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseSiswaResponse {
    private String courseId;
    private String siswaId;
    private String siswaName;
}
