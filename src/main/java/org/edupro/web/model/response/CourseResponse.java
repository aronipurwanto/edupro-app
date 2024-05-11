package org.edupro.web.model.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CourseResponse {
    private String id;
    private String name;
    private String description;
    private String shortName;
    private Boolean shown;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String summary;
    private Long imageId;
    private Integer format;
    private Integer hiddenSection;
    private Integer layout;
    private Boolean completionTracking;
    private String mapelId;
    private String kodeMapel;
    private String kodeLevel;
    private List<CourseSectionRes> sections = new ArrayList<>();
}
