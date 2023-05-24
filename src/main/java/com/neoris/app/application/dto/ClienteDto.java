package com.neoris.app.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDto {
    private Long id;

    @Valid
    @NotNull(message = "Los datos de la persona son obligatorios")
    private PersonaDto persona;

    @NotNull(message = "La contraseña es obligatoria")
    @NotEmpty(message = "La contraseña es obligatoria")
    @Size(max = 4, message = "La contraseña es demasiado larga, por favor verifique la información")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "La contraseña debe contener solo letras y números")
    private String contrasena;

    @NotNull(message = "El estado del cliente es obligatorio")
    private Boolean estado;
}
