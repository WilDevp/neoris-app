package com.neoris.app.infrastructure.port.out.cuenta.repository;

import com.neoris.app.infrastructure.port.out.cuenta.entity.CuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CuentaRepository extends JpaRepository<CuentaEntity,Long> {
    Optional<CuentaEntity> findByNumeroCuenta(String numeroCuenta);
}
