package com.neoris.app.infrastructure.port.in;


import com.neoris.app.application.port.in.CuentaPort;
import com.neoris.app.application.port.in.command.CuentaCommand;
import com.neoris.app.application.port.out.response.ResponseGeneric;
import com.neoris.app.application.port.out.response.ResponseServices;
import com.neoris.app.domain.exception.ArgumentsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/cuenta")
public class CuentaController {
    private final CuentaPort cuentaPort;

    public CuentaController(CuentaPort cuentaPort){
        this.cuentaPort = cuentaPort;
    }

    @PostMapping
    private ResponseEntity crearCuenta(@Valid @RequestBody CuentaCommand cuentaCommand, BindingResult resultRequest){
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
            return cuentaPort.crearCuenta(cuentaCommand);
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

    @GetMapping
    private ResponseEntity obtenerCuenta(@RequestParam(name = "idCuenta") Long idCuenta){
        return cuentaPort.obtenerCuentaPorId(idCuenta);
    }

    @PutMapping
    private ResponseEntity editarCuenta (@Valid @RequestBody CuentaCommand cuentaCommand, BindingResult resultRequest){
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
            return cuentaPort.editarCuenta(cuentaCommand);
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
    private ResponseEntity actualizarCuenta (@RequestParam(name = "idCuenta") Long idCuenta, @RequestBody CuentaCommand cuentaCommand){
        try{
           return cuentaPort.actualizarCuenta(idCuenta,cuentaCommand);
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
    private ResponseEntity eliminarCuenta(@RequestParam(name = "idCuenta") Long idCuenta ){
        return cuentaPort.eliminarCuenta(idCuenta);
    }
}
