package org.edupro.web.building.model;

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
public class RoomReq {

    private String id;

    @NotEmpty(message = "kode wajib diisi")
    @Size(min = 5, max = 10, message = "kode minimal 4 dan maksimal 10")
    private String code;

    @NotEmpty(message = "nama tidak boleh kosong")
    @Size(min = 5, max = 100, message = "nama minimal 4 dan maksimal 10")
    private String name;

    @NotNull(message = "kapasitas tidak boleh kosong")
    private Integer capacity;

    @NotEmpty(message = "kode gedung tidak boleh kosong")
    private String buildingId;

    private String buildingName;

}
