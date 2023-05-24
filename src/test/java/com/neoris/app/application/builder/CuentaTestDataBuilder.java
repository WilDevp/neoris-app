package com.neoris.app.application.builder;

import com.neoris.app.application.dto.CuentaDto;
import com.neoris.app.application.port.in.command.CuentaCommand;

public class CuentaTestDataBuilder {

    private CuentaDto cuenta;

    public CuentaTestDataBuilder conCuenta(CuentaDto cuenta) {
        this.cuenta=cuenta;
        return this;
    }

    public CuentaCommand buildComando() {
        return new CuentaCommand(cuenta);
    }
}
