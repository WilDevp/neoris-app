package com.neoris.app.application.port.out;


import com.neoris.app.domain.model.Cliente;
import com.neoris.app.domain.model.Cuenta;

import java.util.Optional;


public interface CuentaServicesPort {
    Cuenta crearCuenta(Cuenta cuenta);
    Optional<Cuenta> obtenerCuentaPorId(Long idCuenta);
    Cuenta editarCuenta(Cuenta cuenta);
    Cuenta actualizarCuenta(Cuenta cuenta);
    Cliente eliminarCuenta(Long id);
    boolean consultarExistenciaCuenta(String numeroCuenta);
}
