package org.edupro.web.curriculum.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CurriculumRes {
    private String id;
    private String code;
    private String name;
    private Integer position;
    private String status;
}
