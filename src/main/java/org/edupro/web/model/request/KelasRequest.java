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
    private Integer id;

    @NotEmpty
    @Size(max = 100)
    private String waliKelas;

    @Size(max = 50)
    private String kodeKelas;

    @Size(max = 50)
    private String level;

    @Size(max = 70)
    private String nameKelas;

    private Integer jumlahSiswa;

    @Size(max = 60)
    private String status;
}
