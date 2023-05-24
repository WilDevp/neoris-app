package com.neoris.app.application.web;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.neoris.app.application.builder.CuentaTestDataBuilder;
import com.neoris.app.application.dto.ClienteDto;
import com.neoris.app.application.dto.CuentaDto;
import com.neoris.app.application.port.in.command.CuentaCommand;
import com.neoris.app.domain.enums.TipoCuentaEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class CuentaTestController {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void crearCuenta() throws Exception
    {
        CuentaCommand cuentaCommand = new CuentaTestDataBuilder()
                                           .conCuenta(CuentaDto.builder()
                                                   .tipoCuenta(TipoCuentaEnum.AHORRO)
                                                   .numeroCuenta("128645")
                                                   .estado(true)
                                                   .saldoInicial(new BigDecimal(700000))
                                                   .cliente(ClienteDto.builder()
                                                                       .id(1L)
                                                                       .build())
                                                               .build()).buildComando();
        mvc.perform( MockMvcRequestBuilders
                        .post("/cuenta")
                        .content(objectMapper.writeValueAsString(cuentaCommand))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void obtenerCuentaPorId() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders
                        .get("/cuenta?idCuenta=1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void editarCuenta() throws Exception {
        CuentaCommand cuentaCommand = new CuentaTestDataBuilder()
                                     .conCuenta(CuentaDto.builder()
                                     .id(5l)
                                     .tipoCuenta(TipoCuentaEnum.AHORRO)
                                     .numeroCuenta("128645")
                                     .estado(true)
                                     .saldoInicial(new BigDecimal(700000))
                                     .cliente(ClienteDto.builder()
                                     .id(1L).build()).build()).buildComando();

        mvc.perform( MockMvcRequestBuilders
                        .put("/cuenta")
                        .content(objectMapper.writeValueAsString(cuentaCommand))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void actualizarCuenta() throws Exception {
        CuentaCommand cuentaCommand = new CuentaTestDataBuilder()
                                                .conCuenta(CuentaDto.builder()
                                                .id(5l)
                                                .tipoCuenta(TipoCuentaEnum.AHORRO)
                                                 .numeroCuenta("128645")
                                                .estado(true)
                                                .saldoInicial(new BigDecimal(100000))
                                                .cliente(ClienteDto.builder()
                                                .id(1L).build()).build()).buildComando();
        mvc.perform( MockMvcRequestBuilders
                        .patch("/cuenta?idCuenta=5")
                        .content(objectMapper.writeValueAsString(cuentaCommand))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void eliminarCuentaOk() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders
                        .delete("/cuenta?idCuenta=10")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
