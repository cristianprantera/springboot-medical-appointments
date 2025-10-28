package com.testeando.turnos.services.interfaces;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import com.testeando.turnos.models.dtos.MedicalAppointmentRequestDTO;
import com.testeando.turnos.models.dtos.MedicalAppointmentResponseDTO;
import com.testeando.turnos.models.entities.Doctor;

public interface IMedicalAppointmentService {
	 MedicalAppointmentResponseDTO createMedicalAppointment(MedicalAppointmentRequestDTO requestDTO);

	Collection<Doctor> getAppointmentsByDoctorAndDate(Doctor doctor, LocalDate localDate);

	List<String> getAvailableHours(int doctorId, String dayString);

	List<MedicalAppointmentResponseDTO> findAllAppointments();
	
	List<String> getAvailableDays(Long doctorId);

	List<MedicalAppointmentResponseDTO> findAppointmentsByDoctor(int doctorId);

	List<MedicalAppointmentResponseDTO> findAppointmentsByPatient(int patientId);

	void deleteById(int id);

}
