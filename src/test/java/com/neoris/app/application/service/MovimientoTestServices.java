package com.neoris.app.application.service;


import com.neoris.app.application.builder.MovimientoTestDataBuilder;
import com.neoris.app.application.dto.CuentaDto;
import com.neoris.app.application.dto.MovimientoDto;
import com.neoris.app.application.port.in.command.MovimientoCommand;
import com.neoris.app.application.port.out.ClienteServicesPort;
import com.neoris.app.application.port.out.CuentaServicesPort;
import com.neoris.app.application.port.out.MovimientoServicesPort;
import com.neoris.app.application.services.MovimientoServices;
import com.neoris.app.domain.enums.TipoCuentaEnum;
import com.neoris.app.domain.enums.TipoMovimientoEnum;
import com.neoris.app.domain.exception.DomainException;
import com.neoris.app.domain.mapper.MovimientoDataMapper;
import com.neoris.app.domain.model.Cliente;
import com.neoris.app.domain.model.Cuenta;
import com.neoris.app.domain.model.Movimientos;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MovimientoTestServices {

    @Test
    public void noExisteCuentaAsociada(){
        MovimientoCommand movimientoCommand = new MovimientoTestDataBuilder()
                .conMovimiento(MovimientoDto.builder()
                        .tipoMovimiento(TipoMovimientoEnum.DEBITO)
                        .fecha(LocalDate.now())
                        .saldo(new BigDecimal(0))
                        .valor(new BigDecimal(200000))
                        .cuenta(CuentaDto.builder()
                                .id(5L).build()).build()).buildComando();

        MovimientoServicesPort movimientoServicesPort = mock(MovimientoServicesPort.class);
        CuentaServicesPort cuentaServicesPort = mock(CuentaServicesPort.class);
        ClienteServicesPort clienteServicesPort = mock(ClienteServicesPort.class);
        MovimientoDataMapper movimientoDataMapper = new MovimientoDataMapper();

        when(cuentaServicesPort.obtenerCuentaPorId(56L)).thenReturn(Optional.empty());

        MovimientoServices movimientoServices = new MovimientoServices(movimientoServicesPort,movimientoDataMapper,
                                                                               cuentaServicesPort,clienteServicesPort);

        DomainException domainException = assertThrows(DomainException.class,
                () -> movimientoServices.crearMovimiento(movimientoCommand));
        assertEquals("No existe una cuenta asociada.", domainException.getMessage());
    }
    @Test
    public void saldoNoDisponible(){
        MovimientoCommand movimientoCommand = new MovimientoTestDataBuilder()
                .conMovimiento(MovimientoDto.builder()
                        .tipoMovimiento(TipoMovimientoEnum.DEBITO)
                        .fecha(LocalDate.now())
                        .saldo(new BigDecimal(0))
                        .valor(new BigDecimal(100000))
                        .cuenta(CuentaDto.builder()
                                .id(5L).build()).build()).buildComando();

        MovimientoServicesPort movimientoServicesPort = mock(MovimientoServicesPort.class);
        CuentaServicesPort cuentaServicesPort = mock(CuentaServicesPort.class);
        ClienteServicesPort clienteServicesPort = mock(ClienteServicesPort.class);
        MovimientoDataMapper movimientoDataMapper = new MovimientoDataMapper();
        Optional<Cuenta> cuenta = dataCuenta();
        when(cuentaServicesPort.obtenerCuentaPorId(5L)).thenReturn(cuenta);

        MovimientoServices movimientoServices = new MovimientoServices(movimientoServicesPort,movimientoDataMapper,
                cuentaServicesPort,clienteServicesPort);

        DomainException domainException = assertThrows(DomainException.class,
                () -> movimientoServices.crearMovimiento(movimientoCommand));
        assertEquals("Saldo no disponible.", domainException.getMessage());
    }

    public Optional<Cuenta> dataCuenta(){
        return Optional.of(Cuenta.builder().tipoCuenta(TipoCuentaEnum.AHORRO)
                                           .id(5L)
                                           .numeroCuenta("8685744")
                                           .estado(true)
                                           .saldoIninial(new BigDecimal(100000))
                                            .cliente(Cliente.builder()
                                            .id(1L)
                                            .build()).build());
    }
    @Test
    public void noExisteMovimientoRangoFecha(){

        MovimientoServicesPort movimientoServicesPort = mock(MovimientoServicesPort.class);
        CuentaServicesPort cuentaServicesPort = mock(CuentaServicesPort.class);
        ClienteServicesPort clienteServicesPort = mock(ClienteServicesPort.class);
        MovimientoDataMapper movimientoDataMapper = new MovimientoDataMapper();
        List<Movimientos> movimientosList = null;
        Optional<Cliente> cliente = dataCliente();
        when(clienteServicesPort.obtenerClientePorId(1L)).thenReturn(cliente);
        when(movimientoServicesPort.obtenerMovimientosPorRangoFecha(LocalDate.now(),LocalDate.now())).thenReturn(movimientosList);

        MovimientoServices movimientoServices = new MovimientoServices(movimientoServicesPort,movimientoDataMapper,
                cuentaServicesPort,clienteServicesPort);

        DomainException domainException = assertThrows(DomainException.class,
                () -> movimientoServices.obtenerReportePorFechas(LocalDate.now(),LocalDate.now(),1L));
        assertEquals("No existe movimientos en el rango de las fechas.", domainException.getMessage());
    }

    public Optional<Cliente> dataCliente(){
        return Optional.of(Cliente.builder()
                                    .id(1L)
                                    .build());
    }

}
