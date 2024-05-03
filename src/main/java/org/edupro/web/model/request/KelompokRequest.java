package org.edupro.web.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KelompokRequest {
    private String id;

    private Integer idLembaga;

    @NotEmpty
    @Size(min = 1, max = 10, message = "kode minimal 1 dan maksimal 10")
    private String kode;

    @NotEmpty
    @Size(min = 5, max = 32, message = "kode minimal 5 dan maksimal 32")
    private String nama;
}
