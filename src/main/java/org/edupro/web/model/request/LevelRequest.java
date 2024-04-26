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
public class LevelRequest {
    private String id;
    @NotNull(message = "Lembaga Id tidak boleh kosong")
    private Integer idLembaga;
    @NotEmpty(message = "kode wajib diisi")
    @Size(min = 1, max = 10, message = "minimal 1 dan maksimal 10")
    private String kode;
    @Size(min = 2, max = 100, message = "minimal 2 dan maksimal 100")
    private String nama;
}
