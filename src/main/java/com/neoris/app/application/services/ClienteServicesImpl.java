package com.neoris.app.application.services;

import com.neoris.app.application.port.in.ClientePort;
import com.neoris.app.application.port.in.command.ClienteCommand;
import com.neoris.app.application.port.out.ClienteServicesPort;
import com.neoris.app.application.port.out.response.ResponseGeneric;
import com.neoris.app.application.port.out.response.ResponseServices;
import com.neoris.app.domain.exception.DomainException;
import com.neoris.app.domain.mapper.ClienteDataMapper;
import com.neoris.app.domain.model.Cliente;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ClienteServicesImpl implements ClientePort {

    private final ClienteServicesPort clienteServicesPort;
    private final ClienteDataMapper clienteDataMapper;

    public ClienteServicesImpl(ClienteServicesPort clienteServicesPort,
                               ClienteDataMapper clienteDataMapper) {
        this.clienteServicesPort = clienteServicesPort;
        this.clienteDataMapper = clienteDataMapper;
    }

    @Override
    public ResponseEntity<?> crearCliente(ClienteCommand cliente) throws DomainException {
        Cliente clienteMapper = clienteDataMapper.commandToDomain(cliente);
        Cliente createdCliente = clienteServicesPort.crearCliente(clienteMapper);

        ResponseGeneric<Cliente> responseGeneric = new ResponseGeneric<>(createdCliente);
        ResponseServices responseServices = ResponseServices.builder()
                .codigo(HttpStatus.OK.value())
                .detalle(HttpStatus.OK.getReasonPhrase())
                .objetoRespuesta(responseGeneric)
                .build();

        return ResponseEntity.ok(responseServices);
    }

    @Override
    public ResponseEntity<?> obtenerClientePorId(Long idCliente) throws DomainException {
        Cliente cliente = clienteServicesPort.obtenerClientePorId(idCliente)
                .orElseThrow(() -> new DomainException("No existe cliente con el ID: " + idCliente, HttpStatus.NOT_FOUND));

        ResponseGeneric<Cliente> responseGeneric = new ResponseGeneric<>(cliente);
        ResponseServices responseServices = ResponseServices.builder()
                .codigo(HttpStatus.OK.value())
                .detalle(HttpStatus.OK.getReasonPhrase())
                .objetoRespuesta(responseGeneric)
                .build();

        return ResponseEntity.ok(responseServices);
    }

    @Override
    public ResponseEntity<?> editarCliente(ClienteCommand cliente) throws DomainException {
        Cliente clienteMapper = clienteDataMapper.commandToDomain(cliente);
        Long clienteId = cliente.getId();

        if (!clienteServicesPort.obtenerClientePorId(clienteId).isPresent()) {
            throw new DomainException("No existe cliente con el ID: " + clienteId, HttpStatus.BAD_REQUEST);
        }

        Cliente editedCliente = clienteServicesPort.editarCliente(clienteMapper);

        ResponseGeneric<Cliente> responseGeneric = new ResponseGeneric<>(editedCliente);
        ResponseServices responseServices = ResponseServices.builder()
                .codigo(HttpStatus.OK.value())
                .detalle(HttpStatus.OK.getReasonPhrase())
                .objetoRespuesta(responseGeneric)
                .build();

        return ResponseEntity.ok(responseServices);
    }

    @Override
    public ResponseEntity<?> actualizarCliente(Long idCliente, ClienteCommand cliente) {
        cliente.setId(idCliente);
        Cliente clienteMapper = clienteDataMapper.commandToDomain(cliente);
        Cliente updatedCliente = clienteServicesPort.actualizarCliente(clienteMapper);

        ResponseGeneric<Cliente> responseGeneric = new ResponseGeneric<>(updatedCliente);
        ResponseServices responseServices = ResponseServices.builder()
                .codigo(HttpStatus.OK.value())
                .detalle(HttpStatus.OK.getReasonPhrase())
                .objetoRespuesta(responseGeneric)
                .build();

        return ResponseEntity.ok(responseServices);
    }

    @Override
    public ResponseEntity<?> eliminarCliente(Long id) throws DomainException {
        Cliente deletedCliente = clienteServicesPort.eliminarCliente(id);

        ResponseGeneric<Cliente> responseGeneric = new ResponseGeneric<>(deletedCliente);
        ResponseServices responseServices = ResponseServices.builder()
                .codigo(HttpStatus.OK.value())
                .detalle(HttpStatus.OK.getReasonPhrase())
                .objetoRespuesta(responseGeneric)
                .build();

        return ResponseEntity.ok(responseServices);
    }
}
