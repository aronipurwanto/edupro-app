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
public class PersonRequest {

    private String id;

    @NotEmpty(message = "Id Tidak Boleh Kosong")
    private String userId;

    @NotEmpty(message = "Nomor Tidak Boleh Kosong")
    private String nomor;

    @NotEmpty(message = "Nama Tidak Boleh Kosong")
    @Size(min = 2, max = 100, message = "Nama Minimal 2 Maksimal 100")
    private String nama;

    @NotEmpty(message = "Alamat Tinggal Tidak Boleh Kosong")
    private String alamatTinggal;

    @NotEmpty(message = "NIK Tidak Boleh Kosong")
    private String nik;

    @NotNull(message = "Tanggal Lahir Tidak Boleh Kosong")
    private LocalDate tanggalLahir;

    @NotEmpty(message = "Tempat Lahir Tidak Boleh Kosong")
    private String tempatLahir;

    @NotEmpty(message = "Jenis Kelamin Tidak Boleh Kosong")
    private String gender;

    @NotEmpty(message = "Agama Tidak Boleh Kosong")
    private String agama;

    @NotEmpty(message = "Golongan Darah Tidak Boleh Kosong")
    private String golDarah;

    @NotEmpty(message = "No Telp Wajib Diisi")
    private String noTelp;

    @NotEmpty(message = "Email Tidak Boleh Kosong")
    private String email;
}
