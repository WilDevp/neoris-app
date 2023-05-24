package com.neoris.app.application.port.in;


import com.neoris.app.application.port.in.command.ClienteCommand;
import com.neoris.app.domain.exception.DomainException;
import org.springframework.http.ResponseEntity;

public interface ClientePort {
    ResponseEntity<?> crearCliente(ClienteCommand cliente) throws DomainException;
    ResponseEntity<?> obtenerClientePorId(Long idCliente) throws DomainException;
    ResponseEntity<?> editarCliente(ClienteCommand cliente) throws DomainException;
    ResponseEntity<?> actualizarCliente(Long idCliente, ClienteCommand cliente);
    ResponseEntity<?> eliminarCliente(Long id) throws DomainException;
}