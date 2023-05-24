package com.neoris.app.infrastructure.port.out.cliente.adapter;


import com.neoris.app.application.port.out.ClienteServicesPort;
import com.neoris.app.domain.model.Cliente;
import com.neoris.app.infrastructure.port.out.cliente.mapper.ClienteDataEntityMapper;
import com.neoris.app.infrastructure.port.out.cliente.repository.ClienteRepository;

import java.util.Optional;

public class ClienteRepositoryImpl implements ClienteServicesPort {

    private final ClienteRepository clienteRepository;
    private final ClienteDataEntityMapper clienteDataEntityMapper;

    public ClienteRepositoryImpl(ClienteRepository clienteRepository,
                                 ClienteDataEntityMapper clienteDataEntityMapper){
        this.clienteRepository = clienteRepository;
        this.clienteDataEntityMapper = clienteDataEntityMapper;
    }


    @Override
    public Cliente crearCliente(Cliente cliente) {
        return clienteDataEntityMapper.entityToDomain(
                clienteRepository.save(clienteDataEntityMapper.domainToEntity(cliente)));
    }

    @Override
    public Optional<Cliente> obtenerClientePorId(Long idCliente) {
        return clienteRepository.findById(idCliente).map(clienteDataEntityMapper::entityToDomain);
    }

    @Override
    public Optional<Cliente> consultarExistencia(String identificacion) {
       return clienteRepository.findByIdentificacion(identificacion)
                               .map(clienteDataEntityMapper::entityToDomain);
    }

    @Override
    public Cliente editarCliente(Cliente cliente) {
        return clienteDataEntityMapper.entityToDomain(
                clienteRepository.save(clienteDataEntityMapper.domainToEntity(cliente)));
    }

    @Override
    public Cliente actualizarCliente(Cliente cliente) {
        return clienteDataEntityMapper.entityToDomain(
                clienteRepository.save(clienteDataEntityMapper.domainToEntity(cliente)));
    }

    @Override
    public Cliente eliminarCliente(Long id) {
        Optional<Cliente> clienteConsultado = obtenerClientePorId(id);
        clienteRepository.deleteById(id);
        return clienteConsultado.get();
    }
}
