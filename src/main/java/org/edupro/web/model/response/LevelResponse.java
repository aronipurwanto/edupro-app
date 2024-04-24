package org.edupro.web.model.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class LevelResponse {
    private String id;
    private Integer idLembaga;
    private String kode;
    private String nama;
    private String status;
}
