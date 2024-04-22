package org.edupro.web.model.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MapelResponse {
    private Integer id;
    private String kelompok;
    private String kurikulum;
    private String kodeMapel;
    private String namaMapel;
    private String level;
    private Integer urutan;
    private Double kkm;
    private String agama;
    private String jadwal;
    private String status;
}
