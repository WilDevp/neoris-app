package com.neoris.app.application.web;


import com.fasterxml.jackson.databind.ObjectMapper;

import com.neoris.app.application.builder.ClienteTestDataBuilder;
import com.neoris.app.application.dto.PersonaDto;
import com.neoris.app.application.port.in.command.ClienteCommand;
import com.neoris.app.domain.enums.GeneroEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class ClienteTestController {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void crearCliente() throws Exception {
        ClienteCommand clienteTestDataBuilder = new ClienteTestDataBuilder()
                .conIdCliente(null)
                .conPersona(PersonaDto.builder()
                        .direccion("test-direccion")
                        .edad(30L)
                        .genero(GeneroEnum.MASCULINO)
                        .nombre("wilmar Garcia")
                        .telefono("3028676522")
                        .identificacion("1000638")
                        .build()).buildComando();
        mvc.perform(MockMvcRequestBuilders
                        .post("/cliente")
                        .content(objectMapper.writeValueAsString(clienteTestDataBuilder))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void obtenerClientePorId() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/cliente?idCliente=1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void editarClienteBadRequest() throws Exception {
        ClienteCommand clienteTestDataBuilder = new ClienteTestDataBuilder()
                .conIdCliente(5L)
                .conPersona(PersonaDto.builder()
                        .id(1L)
                        .direccion("test-direccion")
                        .edad(30L)
                        .genero(GeneroEnum.MASCULINO)
                        .nombre("wilmar Garcia")
                        .telefono("3028676522")
                        .identificacion("1000638")
                        .build()).buildComando();
        mvc.perform(MockMvcRequestBuilders
                        .put("/cliente")
                        .content(objectMapper.writeValueAsString(clienteTestDataBuilder))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void actualizarCliente() throws Exception {
        ClienteCommand clienteTestDataBuilder = new ClienteTestDataBuilder()
                .conIdCliente(1L)
                .conPersona(PersonaDto.builder()
                        .id(1L)
                        .direccion("Medellin")
                        .edad(30L)
                        .genero(GeneroEnum.MASCULINO)
                        .nombre("wilmar Garcia")
                        .telefono("3028676522")
                        .identificacion("1000638")
                        .build()).buildComando();
        mvc.perform(MockMvcRequestBuilders
                        .patch("/cliente?idCliente=1")
                        .content(objectMapper.writeValueAsString(clienteTestDataBuilder))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void eliminarClienteBadRequest() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .delete("/cliente?idCliente=1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
