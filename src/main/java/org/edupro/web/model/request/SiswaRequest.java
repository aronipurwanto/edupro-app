package org.edupro.web.model.request;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SiswaRequest {

    private String id;

    @NotEmpty(message = "nisn wajib di isi")
    @Size(max = 30)
    private String nisn;

    @NotEmpty( message = "Nama wajib di isi")
    @Size(max = 200)
    private String nama;

    @NotEmpty(message = "tempat lahir wajib di isi")
    @Size(max = 20)
    private String kotaTempatLahir;

    @NotNull(message = "tanggal lahir tidak boleh kosong")
    private LocalDate tanggalLahir;
}
