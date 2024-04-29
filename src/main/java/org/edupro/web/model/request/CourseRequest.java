package org.edupro.web.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import lombok.*;


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

    @NotEmpty(message = "start date tidak boleh kosong")
    private String startDate;

    @NotEmpty(message = "end date tidak boleh kosong")
    private String endDate;

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

    @NotEmpty (message = "mapel tidak boleh kosong")
    private String mapelId;

    @NotEmpty(message = "kode level tidak boleh kosong")
    private String kodeLevel;
}
