package org.edupro.web.academic.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AcademicYearRes {
    private String id;
    private String nama;
    private String kurikulumId;
    private String kodeKurikulum;
    private String status;
}
