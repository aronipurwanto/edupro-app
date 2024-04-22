package org.edupro.web.model.request;

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
public class UjianRequest {
    private Integer id;

    @NotEmpty
    @Size(max = 120)
    private String kodeUjian;

    @Size(max = 120)
    private String namaUmum;

    @Size(max = 130)
    private String namaUjianIkm;

    @Size(max = 130)
    private String namaUjian;

    @Size(max = 20)
    private String statusGuru;

    @Size(max = 20)
    private String statusSiswa;
}
