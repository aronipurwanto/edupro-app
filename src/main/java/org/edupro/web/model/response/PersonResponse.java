package org.edupro.web.model.response;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PersonResponse {
    private String id;
    private String userId;
    private String nomor;
    private String nama;
    private String alamatTinggal;
    private String nik;
    private LocalDate tanggalLahir;
    private String tempatLahir;
    private String gender;
    private String agama;
    private String golDarah;
    private String noTelp;
    private String email;
    private String status;
}
