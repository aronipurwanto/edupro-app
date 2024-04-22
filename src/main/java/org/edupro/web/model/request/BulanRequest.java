package org.edupro.web.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BulanRequest {
    private Integer id;
    @NotEmpty(message = "bulan wajib di isi")
    @Size(min = 5, max = 100, message = "minimal 5 dan maximal 100")
    private String bulan;
    @NotEmpty(message = "tahun pelajaran wajib di isi")
    @Size(min = 5, max = 50, message = "minimal 5 dan maximal 50")
    private String tp;
    private String detail;
}
