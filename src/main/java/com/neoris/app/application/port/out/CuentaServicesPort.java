package com.neoris.app.application.port.out;


import com.neoris.app.domain.model.Cuenta;

import java.util.Optional;


public interface CuentaServicesPort {
    Cuenta crearCuenta(Cuenta cuenta);
    Optional<Cuenta> obtenerCuentaPorId(Long idCuenta);
    Cuenta editarCuenta(Cuenta cuenta);
    Cuenta actualizarCuenta(Cuenta cuenta);
    Cuenta eliminarCuenta(Long id);
    Boolean consultarExistenciaCuenta(String numeroCuenta);
}
