package org.edupro.web.model.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KelompokResponse {
    private Integer idLembaga;
    private String kode;
    private String nama;
    private String status;
}
