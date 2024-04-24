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
public class GedungRequest {
    private String id;
    @NotEmpty(message = "kode wajib di isi")
    @Size(min = 1, max = 10, message = "minimal 1 dan maximal 10")
    private String kode;
    @Size(min = 5, max = 100, message = "minimal 5 dan maximal 100")
    private String nama;
}
