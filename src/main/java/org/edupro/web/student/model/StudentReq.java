package org.edupro.web.student.model;


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
public class StudentReq {
    private String id;

    @NotEmpty(message = "nisn wajib di isi")
    @Size(max = 30)
    private String nisn;

    @NotEmpty( message = "Nama wajib di isi")
    @Size(max = 200)
    private String name;

    @NotEmpty(message = "pob wajib di isi")
    @Size(min = 4, max = 100, message = "pob minimal 4 maximal 100")
    private String pob;

    @NotNull(message = "dob wajib di isi")
    private LocalDate dob;

    @NotEmpty(message = "Jenis Kelamin tidak boleh kosong")
    private String gender;

    @NotEmpty(message = "Agama tidak boleh kosong")
    private String religion;

    @NotEmpty(message = "Golongan Darah tidak boleh kosong")
    private String bloodType;

    @NotEmpty(message = "No Telepon tidak boleh kosong")
    private String noTelp;

    @Email(message = "Email harus berformat email")
    private String email;
}
