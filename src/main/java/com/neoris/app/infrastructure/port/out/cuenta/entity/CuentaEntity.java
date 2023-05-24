package com.neoris.app.infrastructure.port.out.cuenta.entity;


import com.neoris.app.domain.enums.TipoCuentaEnum;
import com.neoris.app.infrastructure.port.out.cliente.entity.ClienteEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
@Data
@Entity
@Builder
@Table(name = "cuenta")
@NoArgsConstructor
@AllArgsConstructor
public class CuentaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 8, nullable = false)
    private Long id;

    @Column(name = "numero_cuenta", length = 6)
    private String numeroCuenta;

    @Column(name = "tipo_cuenta", length = 9)
    private TipoCuentaEnum tipoCuenta;

    @Column(name = "saldo_inicial", length = 9)
    private BigDecimal saldoInicial;

    @Column(name = "estado", length = 9)
    private Boolean estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    private ClienteEntity cliente;
}
