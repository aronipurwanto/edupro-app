package org.edupro.web.model.response;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LembagaResponse {
    private String id;
    private String nama;
    private String namaSingkat;
    private String nomorInduk;
    private String kode;
    @DateTimeFormat(pattern = "YYYY-MM-dd")
    private LocalDate tanggalAkhirMasaBerlaku;
    private String kodeJenjangKategori;
    private String kepala;
    private String nomorUnik;
    private String admin;
    private int maxUserUjian;
    private int maxUserLMS;
    private int selisihJamDenganServer;
    private int hariEfektifSekolah;
    private String masukAwal;
    private String masukAkhir;
    private String pulangAwal;
    private String pulahgAkhir;
    private String kodeProvinsi;
    private String kodeKota;
    private String kodeKecamatan;
    private String kodeKelurahan;
    private String alamat;
    private String kodePos;
    private String telpon;
    private String fax;
    private String website;
    private String email;

    private Long kopId;
    private String kopUrl;
    private Long ttdPimpinanId;
    private String ttdPimpinanUrl;
    private Long logoDinasId;
    private String logoDinasUrl;
    private Long logoLembagaId;
    private String logoLembagaUrl;
    private Long stempelId;
    private String stempelUrl;
}
