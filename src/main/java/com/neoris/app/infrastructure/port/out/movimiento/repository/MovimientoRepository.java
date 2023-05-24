package com.neoris.app.infrastructure.port.out.movimiento.repository;

import com.neoris.app.infrastructure.port.out.movimiento.entity.MovimientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;


public interface MovimientoRepository extends JpaRepository<MovimientoEntity,Long> {
    @Query("SELECT M FROM MovimientoEntity M WHERE M.fecha BETWEEN :fechaInicial AND :fechaFinal")
    List<MovimientoEntity> obtenerMovimientosPorRangoFecha(LocalDate fechaInicial, LocalDate fechaFinal);
}
