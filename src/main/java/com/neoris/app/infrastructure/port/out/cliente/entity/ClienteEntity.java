package com.neoris.app.infrastructure.port.out.cliente.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "cliente")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 8, nullable = false)
    private Long id;

    @Column(name = "contrasena", length = 4)
    private String contrasena;

    @Column(name = "estado", length = 10 )
    private Boolean estado;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_persona", nullable = false)
    private PersonaEntity persona;
}
