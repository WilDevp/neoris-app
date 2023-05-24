package com.neoris.app.domain.mapper;

import com.neoris.app.application.port.in.command.ClienteCommand;
import com.neoris.app.domain.model.Cliente;
import com.neoris.app.domain.model.Persona;
import org.springframework.stereotype.Component;

@Component
public class ClienteDataMapper {

    public Cliente commandToDomain(ClienteCommand clienteCommand){
        return Cliente.builder()
                .id(clienteCommand.getId())
                .contrasena(clienteCommand.getContrasena())
                .estado(clienteCommand.getEstado())
                .persona(Persona.builder()
                        .id(clienteCommand.getPersona().getId())
                        .nombre(clienteCommand.getPersona().getNombre())
                        .identificacion(clienteCommand.getPersona().getIdentificacion())
                        .genero(clienteCommand.getPersona().getGenero())
                        .direccion(clienteCommand.getPersona().getDireccion())
                        .edad(clienteCommand.getPersona().getEdad())
                        .telefono(clienteCommand.getPersona().getTelefono())
                        .build())
                .build();
    }
}
