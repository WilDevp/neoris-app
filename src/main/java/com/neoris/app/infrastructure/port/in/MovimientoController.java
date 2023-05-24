package com.neoris.app.infrastructure.port.in;


import com.neoris.app.application.port.in.MovimientoPort;
import com.neoris.app.application.port.in.command.MovimientoCommand;
import com.neoris.app.application.port.out.response.ResponseGeneric;
import com.neoris.app.application.port.out.response.ResponseServices;
import com.neoris.app.domain.exception.ArgumentsException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@RequestMapping(value = "/movimiento")
public class MovimientoController {

    private final MovimientoPort movimientoPort;

    public MovimientoController(MovimientoPort movimientoPort){
        this.movimientoPort = movimientoPort;
    }

    @PostMapping
    private ResponseEntity crearMovimiento(@Valid @RequestBody MovimientoCommand movimientoCommand, BindingResult resultRequest){
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
            return movimientoPort.crearMovimiento(movimientoCommand);
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
    private ResponseEntity obtenerMovimiento(@RequestParam(name = "idMovimiento") Long idMovimiento){
        return movimientoPort.obtenerMovimientoPorId(idMovimiento);
    }

    @PutMapping
    private ResponseEntity editarmovimiento (@Valid @RequestBody MovimientoCommand movimientoCommand, BindingResult resultRequest){
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
            return movimientoPort.editarMovimiento(movimientoCommand);
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
    private ResponseEntity actualizarMovimiento (@RequestParam(name = "idMovimiento") Long idMovimiento, @RequestBody MovimientoCommand movimientoCommand){
        try{
           return movimientoPort.actualizarMovimiento(idMovimiento,movimientoCommand);
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
    private ResponseEntity eliminarMovimiento(@RequestParam(name = "idMovimiento") Long idMovimiento){
        return movimientoPort.eliminarMovimiento(idMovimiento);
    }

    @GetMapping("/obtenerReporte")
    private ResponseEntity obtenerReportePorFechas(@RequestParam(name = "fechainicial")
                                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicial,
                                                   @RequestParam(name = "fechafinal")
                                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFinal,
                                                   @RequestParam(name = "idcliente") Long idcliente) {
        try{
            return movimientoPort.obtenerReportePorFechas(fechaInicial, fechaFinal, idcliente);
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
