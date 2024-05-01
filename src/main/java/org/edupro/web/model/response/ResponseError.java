package org.edupro.web.model.response;

import lombok.*;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ResponseError {
    private int statusCode;
    private String message;
    private List<Map<String,String>> errors;
}
