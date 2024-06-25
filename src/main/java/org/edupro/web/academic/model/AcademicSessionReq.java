package org.edupro.web.academic.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AcademicSessionReq {
    private String id;

    @NotNull(message = "Tahun Ajaran Id wajib diisi")
    @Size(min = 32, max = 36, message = "Tahun Ajaran Id minimal 32 dan maksimal 36")
    private String academicYearId;
    private String academicYearName;

    @NotNull(message = "Semester wajid diisi")
    private Integer semester;

    @NotNull(message = "startDate tidak bolek kosong")
    private LocalDate startDate;

    @NotNull(message = "endDate tidak boleh kosong")
    private LocalDate endDate;
}
