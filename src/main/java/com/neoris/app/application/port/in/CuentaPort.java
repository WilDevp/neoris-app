package com.neoris.app.application.port.in;


import com.neoris.app.application.port.in.command.CuentaCommand;
import com.neoris.app.domain.exception.DomainException;
import org.springframework.http.ResponseEntity;

public interface CuentaPort {
    ResponseEntity<?> crearCuenta(CuentaCommand cuentaCommand) throws DomainException;

    ResponseEntity<?> obtenerCuentaPorId(Long idCuenta) throws DomainException;

    ResponseEntity<?> editarCuenta(CuentaCommand cuentaCommand) throws DomainException;

    ResponseEntity<?> actualizarCuenta(Long idCuenta, CuentaCommand cuentaCommand);

    ResponseEntity<?> eliminarCuenta(Long idCuenta) throws DomainException;
}
