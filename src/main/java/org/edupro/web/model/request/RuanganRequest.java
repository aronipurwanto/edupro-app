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
    @Size(min = 5, max = 10, message = "kode minimal 4 dan maksimal 10")
    private String kode;

    @NotEmpty(message = "nama tidak boleh kosong")
    @Size(min = 5, max = 100, message = "nama minimal 4 dan maksimal 10")
    private String nama;

    @NotNull(message = "kapasitas tidak boleh kosong")
    private Integer kapasitas;

    @NotEmpty(message = "kode gedung tidak boleh kosong")
    private String gedungId;

}
