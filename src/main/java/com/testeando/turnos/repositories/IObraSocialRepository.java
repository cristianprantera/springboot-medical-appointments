package com.testeando.turnos.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testeando.turnos.models.entities.ObraSocial;

public interface IObraSocialRepository extends JpaRepository<ObraSocial,Serializable>{

}
