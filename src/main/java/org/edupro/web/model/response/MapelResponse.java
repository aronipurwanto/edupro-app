package org.edupro.web.model.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MapelResponse {
    private String id;
    private String kode;
    private String nama;
    private String status;
}
