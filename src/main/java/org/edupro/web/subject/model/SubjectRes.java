package org.edupro.web.subject.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubjectRes {
    private String id;
    private String kode;
    private String nama;
    private String status;
}
