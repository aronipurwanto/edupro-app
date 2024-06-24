package org.edupro.web.academic.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AcademicYearReq {
    private String id;

    @NotEmpty(message = "nama wajib di isi")
    @Size(min = 5, max = 200, message = "minimal 5 dan maximal 200")
    private String name;

    @NotEmpty(message = "kode kurikulum wajib di isi")
    private String curriculumId;

    private String curriculumName;

    @NotNull(message = "startDate tidak bolek kosong")
    private LocalDate startDate;

    @NotNull(message = "endDate tidak boleh kosong")
    private LocalDate endDate;
}
