package org.edupro.web.course.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EkskulRes {
    private Integer id;
    private String nama;
    private String singkatan;
    private String status;
}
