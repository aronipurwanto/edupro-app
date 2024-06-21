package org.edupro.web.lookup.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LookupReq {
    private String id;

    @NotEmpty(message = "Group Tidak Boleh Kosong")
    private String group;

    @NotEmpty(message = "Kode Tidak Boleh Kosong")
    @Size(min = 2, max = 32, message = "Kode Minimal 2 dan Maksimal 32")
    private String code;

    @NotEmpty(message = "Nama Tidak Boleh Kosong")
    @Size(min = 4, max = 128, message = "Nama Minimal 4 dan Maximal 128")
    private String name;

    @NotNull(message = "Urutan Tidak Boleh Kosong")
    private Integer position;
}
