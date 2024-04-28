package org.edupro.web.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SesiRequest {
    private String id;

    @NotNull(message = "Tahun Ajaran Id wajib diisi")
    @Size(min = 36, max = 36, message = "Tahun Ajaran Id minimal 36 dan maksimal 36")
    private String tahunAjaranId;

    @NotNull(message = "Semester wajid diisi")
    private Integer semester;

    @NotNull(message = "Kurikulum Id wajid diisi")
    @Size(min = 36, max = 36, message = "Kurikulum Id minimal 36 dan maksimal 36")
    private String kurikulumId;

    @NotEmpty(message = "Kode Kurikulum wajid diisi")
    @Size(min = 2, max = 20, message = "Kode kurikulum minimal 2 dan maksimal 20")
    private String kodeKurikulum;
}
