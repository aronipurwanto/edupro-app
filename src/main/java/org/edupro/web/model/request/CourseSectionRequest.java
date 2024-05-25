package org.edupro.web.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseSectionRequest {
    private String id;
    private String courseId;
    private String sectionType;
    private String name;
    private String description;
    private String parentId;
    private Integer noUrut;
}
