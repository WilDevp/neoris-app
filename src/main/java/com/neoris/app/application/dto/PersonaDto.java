package com.neoris.app.application.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import com.neoris.app.domain.enums.GeneroEnum;
import com.neoris.app.domain.enums.enumImplConvert.GeneroEnumConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class PersonaDto {
    private Long id;

    @NotNull(message = "El nombre es obligatorio")
    @NotEmpty(message = "El nombre es obligatorio")
    @Size(max = 50, message = "El nombre es demasiado largo, por favor verifique la información")
    private String nombre;

    @JsonDeserialize(converter = GeneroEnumConverter.class)
    private GeneroEnum genero;

    @NotNull(message = "La edad es obligatoria")
    private Long edad;

    @NotNull(message = "El número de identificación es obligatorio")
    @NotEmpty(message = "El número de identificación es obligatorio")
    @Size(max = 10, message = "El número de identificación es demasiado largo, por favor verifique la información")
    private String identificacion;

    @NotNull(message = "La dirección es obligatoria")
    @NotEmpty(message = "La dirección es obligatoria")
    @Size(max = 60, message = "La dirección es demasiado larga, por favor verifique la información")
    private String direccion;

    @NotNull(message = "El teléfono es obligatorio")
    @NotEmpty(message = "El teléfono es obligatorio")
    @Size(max = 10, message = "El número de teléfono es demasiado largo, por favor verifique la información")
    private String telefono;
}