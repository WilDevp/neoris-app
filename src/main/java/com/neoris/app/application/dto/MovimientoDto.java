package com.neoris.app.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import com.neoris.app.domain.enums.TipoMovimientoEnum;
import com.neoris.app.domain.enums.enumImplConvert.MovimientoEnumConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoDto implements Serializable {
    private Long id;

    @NotNull(message = "La fecha es obligatoria")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fecha;

    @JsonDeserialize(converter = MovimientoEnumConverter.class)
    private TipoMovimientoEnum tipoMovimiento;

    @NotNull(message = "El valor del movimiento es obligatorio")
    private BigDecimal valor;

    @NotNull(message = "El saldo del movimiento es obligatorio")
    private BigDecimal saldo;

    @Valid
    @NotNull(message = "La cuenta es obligatoria")
    private CuentaDto cuenta;
}
