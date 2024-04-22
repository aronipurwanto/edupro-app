package org.edupro.web.model.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UjianResponse {
    private Integer id;
    private String kodeUjian;
    private String namaUmum;
    private String namaUjianIkm;
    private String namaUjian;
    private String statusGuru;
    private String statusSiswa;
}
