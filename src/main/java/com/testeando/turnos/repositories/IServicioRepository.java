package com.testeando.turnos.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testeando.turnos.models.entities.Servicio;

public interface IServicioRepository extends JpaRepository<Servicio,Serializable> {

	List<Servicio> findBySpecialtyIdSpecialty(Long idSpecialty);

}
