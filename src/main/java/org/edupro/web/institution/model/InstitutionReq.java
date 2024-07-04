package org.edupro.web.institution.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotEmpty(message = "name wajib di isi")
    @Size( max = 100, message = "maksimal 100")
    private String name;

    @NotEmpty(message = "short name wajib di isi")
    @Size(max = 50, message = "maksimal 50")
    private String shortName;

    @NotEmpty(message = "regNumber wajib di isi")
    @Size(max = 100, message = "maksimal 100")
    private String regNumber;

    @NotEmpty(message = "regNumber wajib di isi")
    @Size(min = 5 , max = 20, message = "minimal 5 dan maksimal 20")
    private String code;

    @NotNull
    private LocalDate expiredDate;

    @NotEmpty(message = "regNumber wajib di isi")
    @Size(max = 20, message = "maksimal 20")
    private String levelCategory;

    @NotEmpty(message = "regNumber wajib di isi")
    @Size(max = 100, message = "maksimal 100")
    private String headmaster;

    @NotEmpty(message = "regNumber wajib di isi")
    @Size(max = 100, message = "maksimal 100")
    private String uniqueNumber;

    @NotEmpty(message = "regNumber wajib di isi")
    @Size(max = 100, message = "maksimal 100")
    private String adminName;

    @NotNull
    private int maxExamUser;
    @NotNull
    private int maxLmsUser;
    @NotNull
    private int diffServerTime;
    @NotNull
    private int effectiveDays;

    @NotEmpty(message = "startedDay wajib di isi")
    @Size(max = 5, message = "maksimal 5")
    private String startedDay;

    @NotEmpty(message = "endDay wajib di isi")
    @Size(max = 5, message = "maksimal 5")
    private String endDay;

    @NotEmpty(message = "endEarly wajib di isi")
    @Size(max = 5, message = "maksimal 5")
    private String endEarly;

    @NotEmpty(message = "endOfDay wajib di isi")
    @Size(max = 5, message = "maksimal 5")
    private String endOfDay;

    @NotEmpty(message = "provinceId wajib di isi")
    @Size(max = 20, message = "maksimal 20")
    private String provinceId;

    @NotEmpty(message = "cityId wajib di isi")
    @Size(max = 20, message = "maksimal 20")
    private String cityId;

    @NotEmpty(message = "districtId wajib di isi")
    @Size(max = 20, message = "maksimal 20")
    private String districtId;

    @NotEmpty(message = "subDistrictId wajib di isi")
    @Size(max = 20, message = "maksimal 20")
    private String subDistrictId;

    @NotEmpty(message = "address wajib di isi")
    @Size(max = 255, message = "maksimal 255")
    private String address;

    @NotEmpty(message = "postalCode wajib di isi")
    @Size(max = 6, message = "maksimal 6")
    private String postalCode;

    @NotEmpty(message = "phoneNumber wajib di isi")
    @Size(max = 50, message = "maksimal 50")
    private String phoneNumber;

    @NotEmpty(message = "faxNumber wajib di isi")
    @Size(max = 20, message = "maksimal 20")
    private String faxNumber;

    @NotEmpty(message = "website wajib di isi")
    @Size(max = 100, message = "maksimal 50")
    private String website;

    @NotEmpty(message = "email wajib di isi")
    @Size(max = 100, message = "maksimal 100")
    private String email;

    @NotEmpty(message = "letterHead wajib di isi")
    private String letterHead;

    @NotEmpty(message = "headOfSignature wajib di isi")
    private String headOfSignature;

    @NotEmpty(message = "serviceLogo wajib di isi")
    private String serviceLogo;

    @NotEmpty(message = "institutionLogo wajib di isi")
    private String institutionLogo;

    @NotEmpty(message = "stamp wajib di isi")
    private String stamp;
}
