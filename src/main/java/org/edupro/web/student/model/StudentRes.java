package org.edupro.web.student.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentRes {
    private String id;
    private String nisn;
    private String name;
    private String pob;
    private String dob;
    private String gender;
    private String religion;
    private String bloodType;
    private String noTelp;
    private String email;
    private String status;
}
