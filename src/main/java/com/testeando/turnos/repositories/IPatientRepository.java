package com.testeando.turnos.repositories;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testeando.turnos.models.entities.MedicalAppointment;
import com.testeando.turnos.models.entities.Patient;

public interface IPatientRepository extends JpaRepository<Patient,Serializable> {
	
    Optional<Patient> findByDni(int dni);
    List<Patient>findAll();
    Optional<Patient> findByEmail(String email);

}
