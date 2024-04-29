package org.edupro.web.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
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
    @NotNull(message = "shown tidak boleh kosong")
    private Boolean shown;
    @NotNull(message = "start date tidak boleh kosong")
    private LocalDate startDate;
    @NotNull(message = "end date tidak boleh kosong")
    private LocalDate endDate;
    @NotEmpty(message = "summary tidak boleh kosong")
    private String summary;
    @NotNull(message = "image id tidak boleh kosong")
    private Long imageId;
    @NotNull(message = "format tidak boleh kosong")
    private Integer format;
    @NotNull(message = "hidden section tidak boleh kosong")
    private Integer hiddenSection;
    @NotNull(message = "layout tidak boleh kosong")
    private Integer layout;
    @NotNull(message = "completion tracking tidak boleh kosong")
    private Boolean completionTracking;
    @NotEmpty
    @Size(min = 32, max = 36, message = "mapel id harus 36 karakter")
    private String mapelId;
    @NotEmpty(message = "kode level tidak boleh kosong")
    private String kodeLevel;
}
