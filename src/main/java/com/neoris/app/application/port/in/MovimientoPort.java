package com.neoris.app.application.port.in;

import com.neoris.app.application.port.in.command.MovimientoCommand;
import com.neoris.app.domain.exception.DomainException;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public interface MovimientoPort {
    ResponseEntity<?> crearMovimiento(MovimientoCommand movimientoCommand) throws DomainException;

    ResponseEntity<?> obtenerMovimientoPorId(Long idMovimiento) throws DomainException;

    ResponseEntity<?> editarMovimiento(MovimientoCommand movimientoCommand) throws DomainException;

    ResponseEntity<?> actualizarMovimiento(Long idMovimiento, MovimientoCommand movimientoCommand);

    ResponseEntity<?> eliminarMovimiento(Long idMovimiento) throws DomainException;

    ResponseEntity<?> obtenerReportePorFechas(LocalDate fechaInicial, LocalDate fechaFinal, Long idCliente) throws DomainException;
}