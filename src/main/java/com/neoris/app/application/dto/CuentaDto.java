package com.neoris.app.application.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import com.neoris.app.domain.enums.TipoCuentaEnum;
import com.neoris.app.domain.enums.enumImplConvert.TipoCuentaEnumConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CuentaDto implements Serializable {
    private Long id;

    @NotNull(message = "El número de cuenta es obligatorio")
    @NotEmpty(message = "El número de cuenta es obligatorio")
    @Size(max = 6, message = "El número de cuenta es demasiado largo, por favor verifique la información")
    private String numeroCuenta;

    @JsonDeserialize(converter = TipoCuentaEnumConverter.class)
    private TipoCuentaEnum tipoCuenta;

    @NotNull(message = "El saldo inicial es obligatorio")
    private BigDecimal saldoInicial;

    @NotNull(message = "El estado de la cuenta es obligatorio")
    private Boolean estado;

    @Valid
    @NotNull(message = "El cliente es obligatorio")
    private ClienteDto cliente;
}
