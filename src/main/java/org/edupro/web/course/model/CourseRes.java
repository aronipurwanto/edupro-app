package org.edupro.web.course.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CourseRes {
    private String id;
    private String name;
    private String description;
    private String shortName;
    private Boolean shown;
    private String startDate;
    private String endDate;
    private String summary;
    private Long imageId;
    private Integer format;
    private Integer hiddenSection;
    private Integer layout;
    private Boolean completionTracking;
    private String subjectId;
    private String subjectName;
    private String levelId;
    private String levelName;
    private String status;
    private List<CourseSectionRes> sections = new ArrayList<>();
}
