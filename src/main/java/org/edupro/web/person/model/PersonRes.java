package org.edupro.web.person.model;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PersonRes {
    private String id;
    private String userId;
    private String nik; // NIK KTP
    private String personNo;
    private String fullName;
    private String address;
    private String dob;
    private String pob;
    private String gender;
    private String religion;
    private String bloodType;
    private String telephone;
    private String email;
    private String status;
}
