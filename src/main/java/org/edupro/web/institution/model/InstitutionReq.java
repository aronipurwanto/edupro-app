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
    @Size(min = 5 , max = 200, message = "minimal 5 dan maksimal 200")
    private String name;

    @NotEmpty(message = "short name wajib di isi")
    @Size(min = 5 , max = 20, message = "minimal 5 dan maksimal 20")
    private String shortName;

    @NotEmpty(message = "regNumber wajib di isi")
    @Size(min = 5 , max = 20, message = "minimal 5 dan maksimal 20")
    private String regNumber;

    @NotEmpty(message = "regNumber wajib di isi")
    @Size(min = 5 , max = 20, message = "minimal 5 dan maksimal 20")
    private String code;

    @NotNull
    private LocalDate expiredDate;

    @NotEmpty(message = "regNumber wajib di isi")
    @Size(min = 5 , max = 20, message = "minimal 5 dan maksimal 20")
    private String levelCategory;

    @NotEmpty(message = "regNumber wajib di isi")
    @Size(min = 5 , max = 20, message = "minimal 5 dan maksimal 20")
    private String headmaster;

    @NotEmpty(message = "regNumber wajib di isi")
    @Size(min = 5 , max = 20, message = "minimal 5 dan maksimal 20")
    private String uniqueNumber;

    @NotEmpty(message = "regNumber wajib di isi")
    @Size(min = 5 , max = 20, message = "minimal 5 dan maksimal 20")
    private String adminName;

    @NotNull
    private int maxExamUser;
    @NotNull(message = "must not be null")
    private int maxLmsUser;
    @NotNull(message = "must not be null")
    private int diffServerTime;
    @NotNull(message = "must not be null")
    private int effectiveDays;

    @NotEmpty(message = "startedDay wajib di isi")
    @Size(min = 5 , max = 20, message = "minimal 5 dan maksimal 20")
    private String startedDay;

    @NotEmpty(message = "endDay wajib di isi")
    @Size(min = 5 , max = 20, message = "minimal 5 dan maksimal 20")
    private String endDay;

    @NotEmpty(message = "endEarly wajib di isi")
    @Size(min = 5 , max = 20, message = "minimal 5 dan maksimal 20")
    private String endEarly;

    @NotEmpty(message = "endOfDay wajib di isi")
    @Size(min = 5 , max = 20, message = "minimal 5 dan maksimal 20")
    private String endOfDay;

    @NotEmpty(message = "provinceId wajib di isi")
    @Size(min = 5 , max = 20, message = "minimal 5 dan maksimal 20")
    private String provinceId;

    @NotEmpty(message = "cityId wajib di isi")
    @Size(min = 5 , max = 20, message = "minimal 5 dan maksimal 20")
    private String cityId;

    @NotEmpty(message = "districtId wajib di isi")
    @Size(min = 5 , max = 20, message = "minimal 5 dan maksimal 20")
    private String districtId;

    @NotEmpty(message = "subDistrictId wajib di isi")
    @Size(min = 5 , max = 20, message = "minimal 5 dan maksimal 20")
    private String subDistrictId;

    @NotEmpty(message = "address wajib di isi")
    @Size(min = 5 , max = 20, message = "minimal 5 dan maksimal 20")
    private String address;

    @NotEmpty(message = "postalCode wajib di isi")
    @Size(min = 5 , max = 20, message = "minimal 5 dan maksimal 20")
    private String postalCode;

    @NotEmpty(message = "phoneNumber wajib di isi")
    @Size(min = 5 , max = 20, message = "minimal 5 dan maksimal 20")
    private String phoneNumber;

    @NotEmpty(message = "faxNumber wajib di isi")
    @Size(min = 5 , max = 20, message = "minimal 5 dan maksimal 20")
    private String faxNumber;

    @NotEmpty(message = "website wajib di isi")
    @Size(min = 5 , max = 20, message = "minimal 5 dan maksimal 20")
    private String website;

    @NotEmpty(message = "email wajib di isi")
    @Size(min = 5 , max = 20, message = "minimal 5 dan maksimal 20")
    private String email;

    @NotEmpty(message = "letterHead wajib di isi")
    @Size(min = 5 , max = 20, message = "minimal 5 dan maksimal 20")
    private String letterHead;

    @NotEmpty(message = "headOfSignature wajib di isi")
    @Size(min = 5 , max = 20, message = "minimal 5 dan maksimal 20")
    private String headOfSignature;

    @NotEmpty(message = "serviceLogo wajib di isi")
    @Size(min = 5 , max = 20, message = "minimal 5 dan maksimal 20")
    private String serviceLogo;

    @NotEmpty(message = "institutionLogo wajib di isi")
    @Size(min = 5 , max = 20, message = "minimal 5 dan maksimal 20")
    private String institutionLogo;

    @NotEmpty(message = "stamp wajib di isi")
    @Size(min = 5 , max = 20, message = "minimal 5 dan maksimal 20")
    private String stamp;
}
