package org.edupro.web.person.model;

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
public class PersonReq {

    private String id;

    @NotEmpty(message = "Id Tidak Boleh Kosong")
    private String userId;

    @NotEmpty(message = "NIK Tidak Boleh Kosong")
    private String nik;

    @NotEmpty(message = "Nomor Tidak Boleh Kosong")
    private String personNo;

    @NotEmpty(message = "Nama Tidak Boleh Kosong")
    @Size(min = 2, max = 100, message = "Nama Minimal 2 Maksimal 100")
    private String fullName;

    @NotEmpty(message = "Alamat Tinggal Tidak Boleh Kosong")
    private String address;

    @NotNull(message = "Tanggal Lahir Tidak Boleh Kosong")
    private LocalDate dob;

    @NotEmpty(message = "Tempat Lahir Tidak Boleh Kosong")
    private String pob;

    @NotEmpty(message = "Jenis Kelamin Tidak Boleh Kosong")
    private String gender;

    @NotEmpty(message = "Agama Tidak Boleh Kosong")
    private String religion;

    @NotEmpty(message = "Golongan Darah Tidak Boleh Kosong")
    private String bloodType;

    @NotEmpty(message = "No Telp Wajib Diisi")
    private String telephone;

    @NotEmpty(message = "Email Tidak Boleh Kosong")
    private String email;
}
