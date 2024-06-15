package org.edupro.web.classroom.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ExamRes {
    private Integer id;
    private String kodeUjian;
    private String namaUmum;
    private String namaUjianIkm;
    private String namaUjian;
    private String statusGuru;
    private String statusSiswa;
}
