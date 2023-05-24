package com.neoris.app.application.web;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.neoris.app.application.builder.MovimientoTestDataBuilder;
import com.neoris.app.application.dto.CuentaDto;
import com.neoris.app.application.dto.MovimientoDto;
import com.neoris.app.application.port.in.command.MovimientoCommand;
import com.neoris.app.domain.enums.TipoMovimientoEnum;
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
import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class MovimientoTestController {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void crearMovimiento() throws Exception
    {
        MovimientoCommand movimientoCommand = new MovimientoTestDataBuilder()
                                                       .conMovimiento(MovimientoDto.builder()
                                                               .tipoMovimiento(TipoMovimientoEnum.CREDITO)
                                                               .fecha(LocalDate.now())
                                                               .saldo(new BigDecimal(700000))
                                                               .valor(new BigDecimal(100000))
                                                               .cuenta(CuentaDto.builder()
                                                                .id(5L).build()).build()).buildComando();

        mvc.perform( MockMvcRequestBuilders
                        .post("/movimiento")
                        .content(objectMapper.writeValueAsString(movimientoCommand))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void obtenerMovimientoPorId() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders
                        .get("/movimiento?idMovimiento=1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void editarMovimiento() throws Exception {
        MovimientoCommand movimientoCommand = new  MovimientoTestDataBuilder()
                .conMovimiento(MovimientoDto.builder()
                        .id(1L)
                        .tipoMovimiento(TipoMovimientoEnum.CREDITO)
                        .fecha(LocalDate.now())
                        .saldo(new BigDecimal(700000))
                        .valor(new BigDecimal(100000))
                        .cuenta(CuentaDto.builder()
                                .id(5L).build()).build()).buildComando();

        mvc.perform( MockMvcRequestBuilders
                        .put("/movimiento")
                        .content(objectMapper.writeValueAsString(movimientoCommand))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void actualizarMovimiento() throws Exception {
        MovimientoCommand movimientoCommand = new  MovimientoTestDataBuilder()
                .conMovimiento(MovimientoDto.builder()
                        .id(1L)
                        .tipoMovimiento(TipoMovimientoEnum.CREDITO)
                        .fecha(LocalDate.now())
                        .saldo(new BigDecimal(800000))
                        .valor(new BigDecimal(200000))
                        .cuenta(CuentaDto.builder()
                                .id(5L).build()).build()).buildComando();

        mvc.perform( MockMvcRequestBuilders
                        .patch("/movimiento?idMovimiento=1")
                        .content(objectMapper.writeValueAsString(movimientoCommand))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void eliminarMovimiento() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders
                        .delete("/movimiento?idMovimiento=3")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
