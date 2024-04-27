package org.edupro.web.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseRequest {
    private String id;
    @NotEmpty(message = "Nama tidak boleh kosong")
    private String name;
    @NotEmpty(message = "shortName tidak boleh kosong")
    private String shortName;
    private Boolean shown;
   // @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime startDate;
  // @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime endDate;
    private String summary;
    private Long imageId;
    private Integer format;
    private Integer hiddenSection;
    private Integer layout;
    private Boolean completionTracking;
    @NotEmpty
    @Size(min = 36, max = 36, message = "mapel id harus 36 karakter")
    private String mapelId;
    private String kodeLevel;
}
