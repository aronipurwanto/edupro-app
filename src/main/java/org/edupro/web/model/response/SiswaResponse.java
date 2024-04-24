package org.edupro.web.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SiswaResponse {

    private String id;
    private String nisn;
    private String nama;
    private String kotaTempatLahir;
    private LocalDate tanggalLahir;
    private String status;
}
