package org.edupro.web.level.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class LevelRes {
    private String id;
    private String idLembaga;
    private String namaLembaga;
    private String kode;
    private String nama;
    private Integer noUrut;
    private String status;
}
