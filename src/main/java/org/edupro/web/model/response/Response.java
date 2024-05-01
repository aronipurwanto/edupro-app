package org.edupro.web.model.response;

import lombok.*;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Response<T> {
    private int statusCode;
    private Object message;
    private T data;
    private List<FieldError> errors = new ArrayList<>();
    private int total;
}
