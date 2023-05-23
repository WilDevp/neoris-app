package com.neoris.app.domain.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.neoris.app.domain.GeneroEnum;
import com.neoris.app.domain.enums.enumImplConvert.GeneroEnumConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Persona {
    private Long id;
    private String nombre;
    @JsonDeserialize(converter = GeneroEnumConverter.class)
    private GeneroEnum genero;
    private Long edad;
    private String identificacion;
    private String direccion;
    private String telefono;
}
