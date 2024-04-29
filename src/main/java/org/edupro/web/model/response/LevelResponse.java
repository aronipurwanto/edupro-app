package org.edupro.web.model.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class LevelResponse {
    private String id;
    private String idLembaga;
    private String namaLembaga;
    private String kode;
    private String nama;
    private Integer noUrut;
    private String status;
}
