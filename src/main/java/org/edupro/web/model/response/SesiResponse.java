package org.edupro.web.model.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SesiResponse {
    private Integer tahunPelajaran;
    private Integer urut;
    private String kodeKurikulum;
    private String status;
}
