package com.neoris.app.application.builder;


import com.neoris.app.application.dto.PersonaDto;
import com.neoris.app.application.port.in.command.ClienteCommand;

public class ClienteTestDataBuilder {
    private static final String CONTRASENA= "1234WD";
    private static final Boolean ESTADO = true;
    private Long idCliente;
    private PersonaDto persona;
    private String contrasena;
    private Boolean estado;

    public ClienteTestDataBuilder() {
        this.contrasena = CONTRASENA;
        this.estado = ESTADO;
    }

    public ClienteTestDataBuilder conContrasena(String contrasena) {
        this.contrasena=contrasena;
        return this;
    }

    public ClienteTestDataBuilder conEstado(Boolean estado) {
        this.estado=estado;
        return this;
    }

    public ClienteTestDataBuilder conIdCliente(Long idCliente) {
        this.idCliente=idCliente;
        return this;
    }


    public ClienteTestDataBuilder conPersona(PersonaDto persona) {
        this.persona=persona;
        return this;
    }

    public ClienteCommand buildComando() {
        return new ClienteCommand(idCliente,persona,contrasena,estado);
    }
}
