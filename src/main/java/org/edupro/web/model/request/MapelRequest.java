package org.edupro.web.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MapelRequest {

    private Integer id;

    @NotEmpty(message = "kelompok wajib di isi")
    private String kelompok;

    @NotEmpty(message = "kurikulum wajib di isi")
    private String kurikulum;

    @NotEmpty(message = "kode mapel wajib di isi")
    private String kodeMapel;

    @NotEmpty(message = "nama mapel wajib di isi")
    private String namaMapel;

    @NotEmpty(message = "level wajib di isi")
    private String level;

    @NotNull(message = "urutan tidak boleh kosong")
    private Integer urutan;

    @NotNull(message = "kkm tidak boleh kosong")
    private Double kkm;

    @NotEmpty(message = "agama wajib di isi")
    private String agama;

    @NotEmpty(message = "jadwal tidak boleh kosong")
    private String jadwal;

    @NotEmpty(message = "status wajib di isi")
    private String status;
}
