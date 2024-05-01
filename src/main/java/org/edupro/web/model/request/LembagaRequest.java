package org.edupro.web.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LembagaRequest {
    private String id;

    @NotEmpty
    @Size(max = 100)
    private String nama;

    @Size(max = 50)
    private String namaSingkat;

    @Size(max = 100)
    private String nomorInduk;

    @Size(max = 100)
    private String kode;

    @DateTimeFormat(pattern = "YYYY-MM-dd")
    private LocalDate tanggalAkhirMasaBerlaku;

    @Size(max = 20)
    private String kodeJenjangKategori;

    @Size(max = 100)
    private String kepala;

    @Size(max = 100)
    private String nomorUnik;

    @Size(max = 100)
    private String admin;

    private int maxUserUjian;

    private int maxUserLMS;

    private int selisihJamDenganServer;

    private int hariEfektifSekolah;

    @Size(max = 5)
    private String masukAwal;
    @Size(max = 5)
    private String masukAkhir;
    @Size(max = 5)
    private String pulangAwal;
    @Size(max = 5)
    private String pulahgAkhir;

    @Size(max = 20)
    private String kodeProvinsi;
    @Size(max = 20)
    private String kodeKota;
    @Size(max = 20)
    private String kodeKecamatan;
    @Size(max = 20)
    private String kodeKelurahan;


    @Size(max = 255)
    private String alamat;
    @Size(max = 6)
    private String kodePos;
    @Size(max = 50)
    private String telpon;
    @Size(max = 20)
    private String fax;
    @Size(max= 100)
    private String website;
    @Size(max= 100)
    private String email;

    private Long kop;
    private Long ttdPimpinan;
    private Long logoDinas;
    private Long logoLembaga;
    private Long stempel;
}
