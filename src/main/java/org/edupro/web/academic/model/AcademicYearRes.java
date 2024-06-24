package org.edupro.web.academic.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AcademicYearRes {
    private String id;
    private String name;
    private String curriculumId;
    private String curriculumCode;
    private String curriculumName;
    private String startDate;
    private String endDate;
    private String status;
}
