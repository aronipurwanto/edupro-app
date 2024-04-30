package org.edupro.web.model.request;


import jakarta.validation.constraints.Email;
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

    @NotNull(message = "tanggal lahir wajib di isi")
    private LocalDate tanggalLahir;

    @NotEmpty(message = "Jenis Kelamin tidak boleh kosong")
    private String gender;

    @NotEmpty(message = "Agama tidak boleh kosong")
    private String agama;

    @NotEmpty(message = "Golongan Darah tidak boleh kosong")
    private String golDarah;

    @NotEmpty(message = "No Telepon tidak boleh kosong")
    private String noTelp;

    @Email(message = "Email harus berformat email")
    private String email;
}
