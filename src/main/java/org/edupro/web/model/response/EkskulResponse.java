package org.edupro.web.model.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EkskulResponse {
    private Integer id;
    private String nama;
    private String singkatan;
    private String status;
}
