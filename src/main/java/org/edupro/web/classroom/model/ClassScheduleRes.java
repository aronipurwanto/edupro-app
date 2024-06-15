package org.edupro.web.classroom.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ClassScheduleRes {
    private Integer id;
    private String tahunPelajaran;
    private String kategori;
    private String semester;
    private String minggu;
    private String jumlahJam;
    private String tanggalMulai;
    private String level;
    private String status;
}
