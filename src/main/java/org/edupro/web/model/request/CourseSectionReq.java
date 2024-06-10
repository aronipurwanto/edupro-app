package org.edupro.web.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseSectionReq {
    private String id;
    @NotEmpty
    @Size(min = 32, max = 36, message = "courseId minimal 32 dan maximal 36")
    private String courseId;
    @NotEmpty
    @Size(min = 4, max = 20, message = "sectionType minimal 4 dan maximal 20")
    private String sectionType;
    @NotEmpty
    @Size(min = 10, message = "name minimal 10")
    private String name;
    private String description;
    private String parentId;
    private Integer noUrut;

    public CourseSectionReq(String courseId, String type) {
        this.courseId = courseId;
        this.sectionType = type;
    }
}
