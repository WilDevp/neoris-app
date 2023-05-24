package com.neoris.app.application.port.out;


import com.neoris.app.domain.model.Cliente;

import java.util.Optional;

public interface ClienteServicesPort {
    Cliente crearCliente(Cliente cliente);
    Optional<Cliente> obtenerClientePorId(Long idCliente);
    Optional<Cliente> consultarExistencia(String identificacion);
    Cliente editarCliente(Cliente cliente);
    Cliente actualizarCliente(Cliente cliente);
    Cliente eliminarCliente(Long id);
}
