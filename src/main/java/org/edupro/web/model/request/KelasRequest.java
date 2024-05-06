package org.edupro.web.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class KelasRequest {
    private String id;

    @NotEmpty(message = "kode wajib diisi")
    @Size(min = 4, max = 10, message = "minimal 4 dan maximal 10")
    private String kode;

    @NotEmpty(message = "nama wajib diisi")
    @Size(min = 2, max = 100, message = "minimal 2 dan maximal 100")
    private String nama;

    @NotEmpty(message = "ruang wajib di isi")
    private String ruangId;


    @NotEmpty(message = "lemabag wajib diisi")
    private String lembagaId;


    @NotEmpty(message = "tahun ajaran wajib diisi")
    private String tahunAjaranId;


    @NotEmpty(message = "level wajib diisi")
    private String levelId;


    @NotEmpty(message = "sesi akademik wajib diisi")
    private String sesiAkademikId;


    @NotEmpty(message = "wali kelas wajib diisi")
    private String waliKelasId;

}
