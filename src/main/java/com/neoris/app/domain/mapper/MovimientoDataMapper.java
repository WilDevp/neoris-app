package com.neoris.app.domain.mapper;


import com.neoris.app.application.port.in.command.MovimientoCommand;
import com.neoris.app.domain.model.Cuenta;
import com.neoris.app.domain.model.Movimientos;
import org.springframework.stereotype.Component;

@Component
public class MovimientoDataMapper {

    public Movimientos commandToDomain(MovimientoCommand movimientoCommand){
       return Movimientos.builder()
                .id(movimientoCommand.getMovimiento().getId())
                .fecha(movimientoCommand.getMovimiento().getFecha())
                .tipoMovimiento(movimientoCommand.getMovimiento().getTipoMovimiento())
                .cuenta(Cuenta.builder()
                        .id(movimientoCommand.getMovimiento().getCuenta().getId())
                        .build())
                .valor(movimientoCommand.getMovimiento().getValor())
                .saldo(movimientoCommand.getMovimiento().getSaldo())
                .build();
    }
}
