package com.neoris.app.infrastructure.port.out.cliente.mapper;


import com.neoris.app.domain.model.Cliente;
import com.neoris.app.domain.model.Persona;
import com.neoris.app.infrastructure.port.out.cliente.entity.ClienteEntity;
import com.neoris.app.infrastructure.port.out.cliente.entity.PersonaEntity;
import org.springframework.stereotype.Component;

@Component
public class ClienteDataEntityMapper {

    public Cliente entityToDomain(ClienteEntity clienteEntity){
        return Cliente.builder()
                .id(clienteEntity.getId())
                .contrasena(clienteEntity.getContrasena())
                .estado(clienteEntity.getEstado())
                .persona(Persona.builder()
                        .id(clienteEntity.getPersona().getId())
                        .nombre(clienteEntity.getPersona().getNombre())
                        .identificacion(clienteEntity.getPersona().getIdentificacion())
                        .genero(clienteEntity.getPersona().getGenero())
                        .direccion(clienteEntity.getPersona().getDireccion())
                        .edad(clienteEntity.getPersona().getEdad())
                        .telefono(clienteEntity.getPersona().getTelefono())
                        .build())
                .build();
    }

    public ClienteEntity domainToEntity(Cliente cliente){
        return ClienteEntity.builder()
                .id(cliente.getId())
                .contrasena(cliente.getContrasena())
                .estado(cliente.getEstado())
                .persona(PersonaEntity.builder()
                        .id(cliente.getPersona().getId())
                        .nombre(cliente.getPersona().getNombre())
                        .identificacion(cliente.getPersona().getIdentificacion())
                        .genero(cliente.getPersona().getGenero())
                        .direccion(cliente.getPersona().getDireccion())
                        .edad(cliente.getPersona().getEdad())
                        .telefono(cliente.getPersona().getTelefono())
                        .build())
                .build();
    }
}
