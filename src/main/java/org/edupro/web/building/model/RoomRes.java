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
    private String code;
    private String name;
    private Integer capacity;
    private String buildingId;
    private String buildingCode;
    private String buildingName;
    private String status;

}
