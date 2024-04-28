package org.edupro.web.model.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SesiResponse {
    private String id;
    private String tahunAjaranId;
    private String tahunAjaranName;
    private String kurikulumId;
    private String kodeKurikulum;
    private String kurikulumName;
    private Integer semester;
    private String status;
}
