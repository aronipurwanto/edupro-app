package org.edupro.web.subject.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubjectRes {
    private String id;
    private String code;
    private String name;
    private String types;
    private String status;
}
