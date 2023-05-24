package com.neoris.app.application.port.out.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseServices {
    private int codigo;
    private String detalle;
    private List<String> listaErrores;
    private String mensajeError;
    private ResponseGeneric objetoRespuesta;
}
