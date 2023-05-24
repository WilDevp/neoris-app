package com.neoris.app.infrastructure.config;

import com.neoris.app.application.port.in.ClientePort;
import com.neoris.app.application.port.in.CuentaPort;
import com.neoris.app.application.port.in.MovimientoPort;
import com.neoris.app.application.port.out.ClienteServicesPort;
import com.neoris.app.application.port.out.CuentaServicesPort;
import com.neoris.app.application.port.out.MovimientoServicesPort;
import com.neoris.app.application.services.ClienteServices;
import com.neoris.app.application.services.CuentaService;
import com.neoris.app.application.services.MovimientoServices;
import com.neoris.app.domain.mapper.ClienteDataMapper;
import com.neoris.app.domain.mapper.CuentaDataMapper;
import com.neoris.app.domain.mapper.MovimientoDataMapper;
import com.neoris.app.infrastructure.port.out.cliente.adapter.ClienteRepositoryImpl;
import com.neoris.app.infrastructure.port.out.cliente.mapper.ClienteDataEntityMapper;
import com.neoris.app.infrastructure.port.out.cliente.repository.ClienteRepository;
import com.neoris.app.infrastructure.port.out.cuenta.adapter.CuentaRepositoryImpl;
import com.neoris.app.infrastructure.port.out.cuenta.mapper.CuentaEntityMapper;
import com.neoris.app.infrastructure.port.out.cuenta.repository.CuentaRepository;
import com.neoris.app.infrastructure.port.out.movimiento.adapter.MovimientoRepositoryImpl;
import com.neoris.app.infrastructure.port.out.movimiento.mapper.MovimientoDataEntityMapper;
import com.neoris.app.infrastructure.port.out.movimiento.repository.MovimientoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public ClientePort clientePort(ClienteServicesPort clienteServicesPort,
                                   ClienteDataMapper clienteDataMapper) {
        return new ClienteServices(clienteServicesPort, clienteDataMapper);
    }

    @Bean
    public CuentaPort cuentaPort(CuentaServicesPort cuentaServicesPort, CuentaDataMapper cuentaDataMapper) {
        return new CuentaService(cuentaServicesPort, cuentaDataMapper);
    }

    @Bean
    public ClienteServicesPort clienteServicesPort(ClienteRepository clienteRepository,
                                                   ClienteDataEntityMapper clienteDataEntityMapper) {
        return new ClienteRepositoryImpl(clienteRepository, clienteDataEntityMapper);
    }

    @Bean
    public CuentaServicesPort cuentaServicesPort(CuentaRepository cuentaRepository,
                                                 CuentaEntityMapper cuentaEntityMapper) {
        return new CuentaRepositoryImpl(cuentaRepository, cuentaEntityMapper);
    }

    @Bean
    public MovimientoServicesPort movimientoServicesPort(MovimientoRepository movimientoRepository,
                                                         MovimientoDataEntityMapper movimientoDataEntityMapper) {
        return new MovimientoRepositoryImpl(movimientoRepository, movimientoDataEntityMapper);
    }

    @Bean
    public MovimientoPort movimientoPort(MovimientoServicesPort movimientoServicesPort,
                                         MovimientoDataMapper movimientoDataMapper,
                                         CuentaServicesPort cuentaServicesPort,
                                         ClienteServicesPort clienteServicesPort) {
        return new MovimientoServices(movimientoServicesPort, movimientoDataMapper, cuentaServicesPort, clienteServicesPort);
    }
}