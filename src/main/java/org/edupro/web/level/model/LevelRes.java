package org.edupro.web.level.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class LevelRes {
    private String id;
    private String institutionId;
    private String institutionName;
    private String code;
    private String name;
    private Integer position;
    private String status;
}
