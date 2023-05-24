package com.neoris.app.application.service;


import com.neoris.app.application.builder.ClienteTestDataBuilder;
import com.neoris.app.application.dto.PersonaDto;
import com.neoris.app.application.port.in.command.ClienteCommand;
import com.neoris.app.application.port.out.ClienteServicesPort;
import com.neoris.app.application.services.ClienteServices;
import com.neoris.app.domain.enums.GeneroEnum;
import com.neoris.app.domain.exception.DomainException;
import com.neoris.app.domain.mapper.ClienteDataMapper;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class ClienteTestService {

    private final Long ID_CLIENTE = 5L;

    @Test
    public void validarCrearCliente(){
        ClienteCommand clienteTestDataBuilder = new ClienteTestDataBuilder()
                .conIdCliente(null).conPersona(PersonaDto.builder()
                                                .direccion("test-direccion")
                                                .edad(30L)
                                                .genero(GeneroEnum.MASCULINO)
                                                .nombre("Wilmar")
                                                .telefono("3028676522")
                                                .identificacion("123459568").build()).buildComando();

        ClienteServicesPort clienteServicesPort = mock(ClienteServicesPort.class);

        ClienteDataMapper clienteDataMapper = new ClienteDataMapper();
        ClienteServices clienteServices = new ClienteServices(clienteServicesPort,clienteDataMapper);
        ResponseEntity responseEntity = clienteServices.crearCliente(clienteTestDataBuilder);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }
    @Test
    public void editarClienteNoExisteUsuario(){
        ClienteCommand clienteTestDataBuilder = new ClienteTestDataBuilder()
                                        .conIdCliente(1L).conPersona(PersonaDto.builder()
                                                            .id(1L)
                                                            .direccion("direccion")
                                                            .edad(30L)
                                                            .genero(GeneroEnum.MASCULINO)
                                                            .nombre("Wilmar GRCIA")
                                                            .telefono("3028676522")
                                                             .identificacion("12345987458")
                                                            .build()).buildComando();

        ClienteServicesPort clienteServicesPort = mock(ClienteServicesPort.class);

        ClienteDataMapper clienteDataMapper = new ClienteDataMapper();
        ClienteServices clienteServices = new ClienteServices(clienteServicesPort,clienteDataMapper);

        DomainException domainException = assertThrows(DomainException.class,
                () -> clienteServices.editarCliente(clienteTestDataBuilder));
        assertEquals("No existe usuario con el id: " + ID_CLIENTE, domainException.getMessage());
    }
}
