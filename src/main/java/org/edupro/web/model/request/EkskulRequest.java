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
public class EkskulRequest {
    private Integer id;

    @NotEmpty(message = "nama tidak boleh kosong")
    @Size(min = 5, max = 100, message = "minimal 5 dan maksimal 100")
    private String nama;

    @NotEmpty(message = "Singkatan wajib di isi")
    private String singkatan;

    @NotEmpty(message = "status wajib di isi")
    private String status;
}
