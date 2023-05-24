package com.neoris.app.application.port.out;


import com.neoris.app.domain.model.Movimientos;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MovimientoServicesPort {
    Movimientos crearMovimiento(Movimientos movimientos) ;
    Optional<Movimientos> obtenerMovimientoPorId(Long idMovimiento) ;
    Movimientos editarMovimiento(Movimientos movimientoDto) ;
    Movimientos actualizarMovimiento(Movimientos movimientoDto) ;
    Movimientos eliminarMovimiento(Long id) ;
    //List<ReporteDto> obtenerReportePorFechas(LocalDate fechaInicial, LocalDate fechaFinal, Integer idCliente);

    List<Movimientos> obtenerMovimientosPorRangoFecha(LocalDate fechaInicial, LocalDate fechaFinal);
}
