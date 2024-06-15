package org.edupro.web.lookup.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LookupRes {
    private String id;
    private String group;
    private String kode;
    private String nama;
    private Integer urutan;
    private String status;
}
