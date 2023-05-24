package com.neoris.app.application.builder;

import com.neoris.app.application.dto.MovimientoDto;
import com.neoris.app.application.port.in.command.MovimientoCommand;

public class MovimientoTestDataBuilder {

    private MovimientoDto movimiento;

    public MovimientoTestDataBuilder conMovimiento(MovimientoDto movimiento) {
        this.movimiento=movimiento;
        return this;
    }

    public MovimientoCommand buildComando() {
        return new MovimientoCommand(movimiento);
    }
}
