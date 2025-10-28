package com.testeando.turnos.repositories;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testeando.turnos.models.dtos.MedicalAppointmentResponseDTO;
import com.testeando.turnos.models.entities.MedicalAppointment;

public interface IMedicalAppointmentRepository extends JpaRepository<MedicalAppointment, Integer> {
    boolean existsByDoctorIdAndDayAndHour(int doctorId, LocalDate day, LocalTime hour);
    boolean existsByPatientIdAndDayAndHour(int patientId,LocalDate day,LocalTime hour);
	List<MedicalAppointment> findAllByDoctor_Id(int doctorId);
    List<MedicalAppointment>findAllByPatient_Id(int patientId);
}
