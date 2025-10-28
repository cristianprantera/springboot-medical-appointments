package com.testeando.turnos.repositories;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testeando.turnos.models.entities.Doctor;

public interface IDoctorRepository extends JpaRepository<Doctor,Serializable> {
	List<Doctor> findBySpecialtyIdSpecialty(Long idSpecialty);

	Optional<Doctor> findByEmail(String email);


}
