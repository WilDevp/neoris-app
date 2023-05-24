package com.neoris.app.domain.mapper;

import com.neoris.app.application.port.in.command.CuentaCommand;
import com.neoris.app.domain.model.Cliente;
import com.neoris.app.domain.model.Cuenta;
import org.springframework.stereotype.Component;

@Component
public class CuentaDataMapper {

    public Cuenta commandToDomain(CuentaCommand cuentaCommand){
       return Cuenta.builder()
                .id(cuentaCommand.getCuenta().getId())
                .estado(cuentaCommand.getCuenta().getEstado())
                .saldoIninial(cuentaCommand.getCuenta().getSaldoInicial())
                .numeroCuenta(cuentaCommand.getCuenta().getNumeroCuenta())
                .tipoCuenta(cuentaCommand.getCuenta().getTipoCuenta())
                .cliente(Cliente.builder()
                        .id(cuentaCommand.getCuenta().getCliente().getId())
                       .build())
                .build();
    }
}
