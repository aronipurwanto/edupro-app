package org.edupro.web.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SesiRequest {
    private Integer id;

    @NotEmpty
    @Size(max = 20)
    private String kodeSesi;

    @Size(max = 30)
    private String namaSesi;
}
