package com.neoris.app.application.service;



import com.neoris.app.application.builder.CuentaTestDataBuilder;
import com.neoris.app.application.dto.ClienteDto;
import com.neoris.app.application.dto.CuentaDto;
import com.neoris.app.application.port.in.command.CuentaCommand;
import com.neoris.app.application.port.out.CuentaServicesPort;
import com.neoris.app.application.services.CuentaService;
import com.neoris.app.domain.enums.TipoCuentaEnum;
import com.neoris.app.domain.exception.DomainException;
import com.neoris.app.domain.mapper.CuentaDataMapper;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CuentaTestService {
    @Test
    public void numeroCuentaExistente() {
        CuentaCommand cuentaCommand = new CuentaTestDataBuilder()
                .conCuenta(CuentaDto.builder().tipoCuenta(TipoCuentaEnum.AHORRO)
                                                .numeroCuenta("0001000")
                                                .estado(true)
                                                .saldoInicial(new BigDecimal(800000))
                                                .cliente(ClienteDto.builder()
                                                .id(1L)
                                                .build()).build()).buildComando();


        CuentaServicesPort cuentaServicesPort = mock(CuentaServicesPort.class);
        CuentaDataMapper cuentaDataMapper = new CuentaDataMapper();

        when(cuentaServicesPort.consultarExistenciaCuenta(cuentaCommand.getCuenta().getNumeroCuenta())).thenReturn(Boolean.TRUE);

        CuentaService cuentaService = new CuentaService(cuentaServicesPort, cuentaDataMapper);

        DomainException domainException = assertThrows(DomainException.class,
                () -> cuentaService.crearCuenta(cuentaCommand));
        assertEquals("El numero de cuenta: " + cuentaCommand.getCuenta().getNumeroCuenta() + " ya existe", domainException.getMessage());
    }
}
