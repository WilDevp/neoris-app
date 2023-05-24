package com.neoris.app.application.port.in.command;

import com.neoris.app.application.dto.CuentaDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuentaCommand {
    private CuentaDto cuenta;
}