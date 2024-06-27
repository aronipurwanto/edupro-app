package org.edupro.web.academic.model;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AcademicSessionRes {
    private String id;
    private String academicYearId;
    private String academicYearName;
    private Integer semester;
    private String startDate;
    private String endDate;
    private String status;
}
