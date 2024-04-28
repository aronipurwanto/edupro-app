package org.edupro.web.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersonRequest {
    private String id;
    @NotEmpty(message = "User Id tidak boleh kosong")
    private String userId;
    @NotEmpty(message = "Nomor wajib diisi")
    private String nomor;
    @Size(min = 2, max = 100, message = "Minimal 2 Maksimal 100")
    private String nama;
    @Size(min = 2, max = 255, message = "Minimal 2 dan maksimal 255")
    private String alamatTinggal;
    @NotEmpty(message = "NIK wajib diisi")
    private String nik;
    @NotEmpty(message = "Tanggal Lahir wajib diisi")
    private String tanggalLahir;
    @NotEmpty(message = "Tempat Lahir wajib diisi")
    private String tempatLahir;
    @NotEmpty(message = "Gender wajib diisi")
    private String gender;
    @NotEmpty(message = "Agama wajib diisi")
    private String agama;
    @NotEmpty(message = "Golongan Darah wajib diisi")
    private String golDarah;
    @NotEmpty(message = "No Tlp wajib diisi")
    private String noTelp;
    @NotEmpty(message = "Email wajib diisi")
    private String email;
}
