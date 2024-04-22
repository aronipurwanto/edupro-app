package org.edupro.web.model.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class LevelResponse {
    private Integer id;
    private String kode;
    private String nama;
    private String status;
    private String riwayat;
}
