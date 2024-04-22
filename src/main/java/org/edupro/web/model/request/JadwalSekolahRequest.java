package org.edupro.web.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class JadwalSekolahRequest {
    private Integer id;
    @NotEmpty(message = "kode wajib diisi")
    @Size(min = 1, max = 10, message = "minimal 1 dan maksimal 10")
    private String kode;
    @Size(min = 5, max = 100, message = "minimal 5 dan maksimal 100")
    private String nama;
    @NotEmpty(message = "status wajib diisi")
    private String status;
    @NotEmpty(message = "riwayat wajib diisi")
    private String riwayat;
}
