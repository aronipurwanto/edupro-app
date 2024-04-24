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
public class RuanganRequest {

    private String id;

    @NotEmpty(message = "kode wajib diisi")
    @Size(min = 4, max = 10, message = "minimal 1 dan maximal 10")
    private String kode;

    @Size(min = 4, max = 100, message = "minimal 5 dan maximal 100")
    private String nama;

    @NotNull(message = "kapasitas tidak boleh kosong")
    private Integer kapasitas;
    private String gedungId;

}
