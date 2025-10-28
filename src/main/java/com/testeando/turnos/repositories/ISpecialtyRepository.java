package com.testeando.turnos.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testeando.turnos.models.entities.Specialty;

public interface ISpecialtyRepository extends JpaRepository<Specialty,Serializable>{

}
