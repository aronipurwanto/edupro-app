package org.edupro.web.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseSectionRes {
    private String id;
    private String courseId;
    private String sectionType;
    private String name;
    private String description;
    private String parentId;
    private Integer noUrut;
}
