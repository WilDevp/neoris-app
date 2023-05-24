package com.neoris.app.infrastructure.port.out.cliente.repository;

import com.neoris.app.infrastructure.port.out.cliente.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<PersonaEntity,Long> {

}
