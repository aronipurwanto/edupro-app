package org.edupro.web.model.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class KelasResponse {
    private Integer id;
    private String waliKelas;
    private String kodeKelas;
    private String level;
    private String nameKelas;
    private Integer jumlahSiswa;
    private String status;
}
