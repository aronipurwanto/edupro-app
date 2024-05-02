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
public class TahunAjaranRequest {
    private String id;

    @NotEmpty(message = "nama wajib di isi")
    @Size(min = 5, max = 200, message = "minimal 5 dan maximal 200")
    private String nama;

    @NotEmpty(message = "kode kurikulum wajib di isi")
    private String kurikulumId;
}
