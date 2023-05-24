package com.neoris.app.infrastructure.port.in;


import com.neoris.app.application.port.in.ClientePort;
import com.neoris.app.application.port.in.command.ClienteCommand;
import com.neoris.app.application.port.out.response.ResponseGeneric;
import com.neoris.app.application.port.out.response.ResponseServices;
import com.neoris.app.domain.exception.ArgumentsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {

    private final ClientePort clientePort;

    public ClienteController(ClientePort clientePort){
        this.clientePort = clientePort;
    }

    @PostMapping
    private ResponseEntity crearCliente(@Valid @RequestBody ClienteCommand cliente, BindingResult resultRequest){
        if (resultRequest.hasErrors()) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ResponseServices.builder()
                            .codigo(HttpStatus.BAD_REQUEST.value())
                            .detalle(HttpStatus.BAD_REQUEST.getReasonPhrase())
                            .listaErrores(ArgumentsException.handleExceptions(resultRequest))
                            .build()
            );
        }
        return clientePort.crearCliente(cliente);
    }

    @GetMapping
    private ResponseEntity obtenerCliente(@RequestParam(name = "idCliente") Long idCliente){
        return clientePort.obtenerClientePorId(idCliente);
    }

    @PutMapping
    private ResponseEntity editarCliente (@Valid @RequestBody ClienteCommand cliente, BindingResult resultRequest){
        if (resultRequest.hasErrors()) {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ResponseServices.builder()
                            .codigo(HttpStatus.BAD_REQUEST.value())
                            .detalle(HttpStatus.BAD_REQUEST.getReasonPhrase())
                            .listaErrores(ArgumentsException.handleExceptions(resultRequest))
                            .build()
            );
        }
        try{
            return clientePort.editarCliente(cliente);
        }catch (Exception ex){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ResponseServices.builder()
                            .codigo(HttpStatus.BAD_REQUEST.value())
                            .detalle(HttpStatus.BAD_REQUEST.getReasonPhrase())
                            .objetoRespuesta(new ResponseGeneric(ex.getMessage()))
                            .build()
            );
        }

    }

    @PatchMapping
    private ResponseEntity actualizarCliente (@RequestParam(name = "idCliente") Long idCliente, @RequestBody ClienteCommand cliente){
        try{
            return clientePort.actualizarCliente(idCliente,cliente);
        }catch (Exception ex){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ResponseServices.builder()
                            .codigo(HttpStatus.BAD_REQUEST.value())
                            .detalle(HttpStatus.BAD_REQUEST.getReasonPhrase())
                            .objetoRespuesta(new ResponseGeneric(ex.getMessage()))
                            .build()
            );
        }
    }

    @DeleteMapping
    private ResponseEntity eliminarCliente(@RequestParam(name = "idCliente") Long idCliente){
        try{
            return clientePort.eliminarCliente(idCliente);
        }catch (Exception ex){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ResponseServices.builder()
                            .codigo(HttpStatus.BAD_REQUEST.value())
                            .detalle(HttpStatus.BAD_REQUEST.getReasonPhrase())
                            .objetoRespuesta(new ResponseGeneric(ex.getMessage()))
                            .build()
            );
        }
    }
}
