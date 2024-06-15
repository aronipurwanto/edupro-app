package org.edupro.web.building.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoomRes {

    private String id;
    private String kode;
    private String nama;
    private Integer kapasitas;
    private String kodeGedung;
    private String namaGedung;
    private String gedungId;
    private String status;

}
