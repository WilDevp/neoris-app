package com.neoris.app.application.services;

import com.neoris.app.application.port.in.CuentaPort;
import com.neoris.app.application.port.in.command.CuentaCommand;
import com.neoris.app.application.port.out.CuentaServicesPort;
import com.neoris.app.application.port.out.response.ResponseGeneric;
import com.neoris.app.application.port.out.response.ResponseServices;
import com.neoris.app.domain.exception.DomainException;
import com.neoris.app.domain.mapper.CuentaDataMapper;
import com.neoris.app.domain.model.Cuenta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CuentaServiceImpl implements CuentaPort {

    private final CuentaServicesPort cuentaServicesPort;
    private final CuentaDataMapper cuentaDataMapper;


    public CuentaServiceImpl(CuentaServicesPort cuentaServicesPort,
                             CuentaDataMapper cuentaDataMapper){
        this.cuentaServicesPort = cuentaServicesPort;
        this.cuentaDataMapper = cuentaDataMapper;
    }


    @Override
    public ResponseEntity crearCuenta(CuentaCommand cuentaCommand) throws DomainException {
        if(cuentaServicesPort.consultarExistenciaCuenta(cuentaCommand.getCuenta().getNumeroCuenta())){
            throw new DomainException("El numero de cuenta: " + cuentaCommand.getCuenta().getNumeroCuenta() + " ya existe",HttpStatus.BAD_REQUEST);
        }
        Cuenta cuentaMapper = cuentaDataMapper.commandToDomain(cuentaCommand);
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseServices.builder()
                        .codigo(HttpStatus.OK.value())
                        .detalle(HttpStatus.OK.getReasonPhrase())
                        .objetoRespuesta(new ResponseGeneric(cuentaServicesPort.crearCuenta(cuentaMapper)))
                        .build()
        );
    }

    @Override
    public ResponseEntity obtenerCuentaPorId(Long idCuenta) throws DomainException{
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseServices.builder()
                        .codigo(HttpStatus.OK.value())
                        .detalle(HttpStatus.OK.getReasonPhrase())
                        .objetoRespuesta(new ResponseGeneric(cuentaServicesPort.obtenerCuentaPorId(idCuenta)))
                        .build()
        );
    }

    @Override
    public ResponseEntity editarCuenta(CuentaCommand cuentaCommand) throws DomainException{
        Cuenta cuentaMapper = cuentaDataMapper.commandToDomain(cuentaCommand);
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseServices.builder()
                        .codigo(HttpStatus.OK.value())
                        .detalle(HttpStatus.OK.getReasonPhrase())
                        .objetoRespuesta(new ResponseGeneric(cuentaServicesPort.editarCuenta(cuentaMapper)))
                        .build()
        );
    }

    @Override
    public ResponseEntity actualizarCuenta(Long idCuenta, CuentaCommand cuentaCommand) {
        cuentaCommand.getCuenta().setId(idCuenta);
        Cuenta cuentaMapper = cuentaDataMapper.commandToDomain(cuentaCommand);
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseServices.builder()
                        .codigo(HttpStatus.OK.value())
                        .detalle(HttpStatus.OK.getReasonPhrase())
                        .objetoRespuesta(new ResponseGeneric(cuentaServicesPort.actualizarCuenta(cuentaMapper)))
                        .build()
        );
    }

    @Override
    public ResponseEntity eliminarCuenta(Long idCuenta) throws DomainException{
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseServices.builder()
                        .codigo(HttpStatus.OK.value())
                        .detalle(HttpStatus.OK.getReasonPhrase())
                        .objetoRespuesta(new ResponseGeneric(cuentaServicesPort.eliminarCuenta(idCuenta)))
                        .build()
        );
    }
}
