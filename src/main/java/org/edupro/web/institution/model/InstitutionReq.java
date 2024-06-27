package org.edupro.web.institution.model;

import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstitutionReq {
    private String id;
    @Size(max = 200)
    private String name;
    private String shortName;
    private String regNumber;
    private String code;
    private LocalDate expiredDate;
    private String levelCategory;
    private String headmaster;
    private String uniqueNumber;
    private String adminName;
    private int maxExamUser;
    private int maxLmsUser;
    private int diffServerTime;
    private int effectiveDays;
    private String startedDay;
    private String endDay;
    private String endEarly;
    private String endOfDay;
    private String provinceId;
    private String cityId;
    private String districtId;
    private String subDistrictId;
    private String address;
    private String postalCode;
    private String phoneNumber;
    private String faxNumber;
    private String website;
    private String email;
    private String letterHead;
    private String headOfSignature;
    private String serviceLogo;
    private String institutionLogo;
    private String stamp;
}
