package org.edupro.web.classroom.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClassReq {
    private String id;

    @NotEmpty(message = "kode wajib diisi")
    @Size(min = 4, max = 10, message = "minimal 4 dan maximal 10")
    private String code;

    @NotEmpty(message = "nama wajib diisi")
    @Size(min = 2, max = 100, message = "minimal 2 dan maximal 100")
    private String name;

    @NotEmpty(message = "ruang wajib di isi")
    private String roomId;


    @NotEmpty(message = "lemabag wajib diisi")
    private String institutionId;


    @NotEmpty(message = "tahun ajaran wajib diisi")
    private String academicYearId;


    @NotEmpty(message = "level wajib diisi")
    private String levelId;


    @NotEmpty(message = "sesi akademik wajib diisi")
    private String academicSessionId;


    @NotEmpty(message = "wali kelas wajib diisi")
    private String homeroomTeacherId;

}
