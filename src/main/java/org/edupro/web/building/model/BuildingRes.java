package org.edupro.web.building.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BuildingRes {
    private String id;
    private String kode;
    private String nama;
    private String status;
}
