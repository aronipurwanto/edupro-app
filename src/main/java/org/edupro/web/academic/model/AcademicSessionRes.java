package org.edupro.web.academic.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AcademicSessionRes {
    private String id;
    private String tahunAjaranId;
    private String tahunAjaranName;
    private String kurikulumId;
    private String kodeKurikulum;
    private String kurikulumName;
    private Integer semester;
    private String status;
}
