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
public class MapelRequest {

    private String id;

    @NotEmpty(message = "kode wajib di isi")
    @Size(min = 2, max = 10, message = "minimal 2 dan maximal 10")
    private String kode;

    @NotEmpty(message = "nama wajib di isi")
    @Size(min = 4, max = 100, message = "nama minimal 4 dan maximal 100")
    private String nama;
}
