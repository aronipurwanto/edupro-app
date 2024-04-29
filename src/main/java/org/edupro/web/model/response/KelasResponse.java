package org.edupro.web.model.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class KelasResponse {
    private String id;
    private String kode;
    private String nama;
    private String ruangId;
    private String kodeRuangan;
    private String lembagaId;
    private String namaLembaga;
    private String tahunAjaranId;
    private String namaTahunAjaran;
    private String levelId;
    private String namaLevel;
    private String sesiAkademikId;
    private String semester;
    private String waliKelasId;
    private String namaWaliKelas;
    private String status;
}
