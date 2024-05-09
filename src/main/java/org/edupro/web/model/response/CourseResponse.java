package org.edupro.web.model.response;

import lombok.*;

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
    private LocalDate startDate;
    private LocalDate endDate;
    private String summary;
    private Long imageId;
    private Integer format;
    private Integer hiddenSection;
    private Integer layout;
    private Boolean completionTracking;
    private String mapelId;
    private String kodeLevel;
    private String kodeMapel;
    private String status;
}
