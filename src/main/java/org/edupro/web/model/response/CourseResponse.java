package org.edupro.web.model.response;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CourseResponse {
    private String id;
    private String name;
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
    private String mapelId;
    private String kodeLevel;
    private String kodeMapel;
    private String status;
}
