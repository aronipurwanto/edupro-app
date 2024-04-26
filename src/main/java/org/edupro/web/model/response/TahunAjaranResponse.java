package org.edupro.web.model.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TahunAjaranResponse {
    private String id;
    private String nama;
    private String kodeKurikulum;
    private String status;
}
