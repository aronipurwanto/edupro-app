package org.edupro.web.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class KurikulumResponse {
    private String id;
    private String kode;
    private String nama;
    private Integer noUrut;
    private String status;
}
