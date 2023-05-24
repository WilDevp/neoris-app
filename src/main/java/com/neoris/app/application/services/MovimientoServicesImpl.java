package com.neoris.app.application.services;



import com.neoris.app.application.port.in.MovimientoPort;
import com.neoris.app.application.port.in.command.MovimientoCommand;
import com.neoris.app.application.port.out.ClienteServicesPort;
import com.neoris.app.application.port.out.CuentaServicesPort;
import com.neoris.app.application.port.out.MovimientoServicesPort;
import com.neoris.app.application.port.out.response.ResponseGeneric;
import com.neoris.app.application.port.out.response.ResponseServices;
import com.neoris.app.domain.enums.TipoMovimientoEnum;
import com.neoris.app.domain.exception.DomainException;
import com.neoris.app.domain.mapper.MovimientoDataMapper;
import com.neoris.app.domain.model.Cliente;
import com.neoris.app.domain.model.Cuenta;
import com.neoris.app.domain.model.Movimientos;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class MovimientoServicesImpl implements MovimientoPort {

    private final MovimientoServicesPort movimientoServicesPort;
    private final MovimientoDataMapper movimientoDataMapper;
    private final CuentaServicesPort cuentaServicesPort;
    private final ClienteServicesPort clienteServicesPort;

    public MovimientoServicesImpl(MovimientoServicesPort movimientoServicesPort,
                                  MovimientoDataMapper movimientoDataMapper,
                                  CuentaServicesPort cuentaServicesPort,
                                  ClienteServicesPort clienteServicesPort){
        this.movimientoServicesPort = movimientoServicesPort;
        this.movimientoDataMapper = movimientoDataMapper;
        this.cuentaServicesPort = cuentaServicesPort;
        this.clienteServicesPort = clienteServicesPort;
    }


    @Override
    public ResponseEntity crearMovimiento(MovimientoCommand movimientoCommand) throws DomainException {

        Optional<Cuenta> cuenta = cuentaServicesPort.obtenerCuentaPorId(movimientoCommand.getMovimiento().getCuenta().getId());
        if(!cuenta.isPresent()){
            throw new DomainException("No existe una cuenta asociada.", HttpStatus.BAD_REQUEST);
        }
        if(movimientoCommand.getMovimiento().getTipoMovimiento().equals(TipoMovimientoEnum.CREDITO)){
           movimientoCommand.getMovimiento().setSaldo(movimientoCommand.getMovimiento().getSaldo()
                                                                       .add(movimientoCommand
                                                                       .getMovimiento().getValor()));
        }else {
            if (movimientoCommand.getMovimiento().getSaldo().equals(BigDecimal.ZERO)) {
                throw new DomainException("Saldo no disponible.", HttpStatus.BAD_REQUEST);
            }
            movimientoCommand.getMovimiento().setSaldo(movimientoCommand.getMovimiento().getSaldo()
                    .subtract(movimientoCommand.getMovimiento().getValor()));
        }

        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseServices.builder()
                        .codigo(HttpStatus.OK.value())
                        .detalle(HttpStatus.OK.getReasonPhrase())
                        .objetoRespuesta(new ResponseGeneric(movimientoServicesPort.crearMovimiento(movimientoDataMapper.commandToDomain(movimientoCommand))))
                        .build()
        );
    }

    @Override
    public ResponseEntity obtenerMovimientoPorId(Long idMovimiento) throws DomainException {
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseServices.builder()
                        .codigo(HttpStatus.OK.value())
                        .detalle(HttpStatus.OK.getReasonPhrase())
                        .objetoRespuesta(new ResponseGeneric(movimientoServicesPort.obtenerMovimientoPorId(idMovimiento)))
                        .build()
        );
    }

    @Override
    public ResponseEntity editarMovimiento(MovimientoCommand movimientoCommand) throws DomainException {
        Movimientos movimientos = movimientoDataMapper.commandToDomain(movimientoCommand);
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseServices.builder()
                        .codigo(HttpStatus.OK.value())
                        .detalle(HttpStatus.OK.getReasonPhrase())
                        .objetoRespuesta(new ResponseGeneric(movimientoServicesPort.editarMovimiento(movimientos)))
                        .build()
        );
    }

    @Override
    public ResponseEntity actualizarMovimiento(Long idMovimiento, MovimientoCommand movimientoCommand) {
        movimientoCommand.getMovimiento().setId(idMovimiento);
        Movimientos movimientos = movimientoDataMapper.commandToDomain(movimientoCommand);
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseServices.builder()
                        .codigo(HttpStatus.OK.value())
                        .detalle(HttpStatus.OK.getReasonPhrase())
                        .objetoRespuesta(new ResponseGeneric(movimientoServicesPort.actualizarMovimiento(movimientos)))
                        .build()
        );
    }

    @Override
    public ResponseEntity eliminarMovimiento(Long idMovimiento) throws DomainException {
        Optional<Movimientos> movimientos = movimientoServicesPort.obtenerMovimientoPorId(idMovimiento);
        if(movimientos.isPresent()){
            movimientoServicesPort.eliminarMovimiento(idMovimiento);
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseServices.builder()
                        .codigo(HttpStatus.OK.value())
                        .detalle(HttpStatus.OK.getReasonPhrase())
                        .objetoRespuesta(new ResponseGeneric(movimientos.get()))
                        .build()
        );
    }

    @Override
    public ResponseEntity obtenerReportePorFechas(LocalDate fechaInicial, LocalDate fechaFinal, Long idCliente) throws DomainException {
        Optional<Cliente> cliente = clienteServicesPort.obtenerClientePorId(idCliente);
        List<Movimientos> movimientosList;
        if(cliente.isPresent()){
             movimientosList = movimientoServicesPort.obtenerMovimientosPorRangoFecha(fechaInicial,fechaFinal);
            if(ObjectUtils.isEmpty(movimientosList)){
               throw new DomainException("No existe movimientos en el rango de las fechas.",HttpStatus.BAD_REQUEST);
            }
        }else{
            throw new DomainException("El idUsuario no existe en el sistema.",HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseServices.builder()
                        .codigo(HttpStatus.OK.value())
                        .detalle(HttpStatus.OK.getReasonPhrase())
                        .objetoRespuesta(new ResponseGeneric(movimientosList))
                        .build()
        );
    }
}
