package org.edupro.web.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SiswaResponse {

    private String id;
    private String nisn;
    private String nama;
    private String kotaTempatLahir;
    private String tanggalLahir;
    private String status;
}
