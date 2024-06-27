package org.edupro.web.classroom.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ClassRes {
    private String id;
    private String code;
    private String name;

    private String roomId;
    private String roomCode;

    private String institutionId;
    private String institutionName;

    private String academicYearId;
    private String academicYearName;

    private String levelId;
    private String levelName;

    private String academicSessionId;
    private String semester;

    private String homeroomTeacherId;
    private String homeroomTeacherName;

    private String status;
}
