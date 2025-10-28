package com.testeando.turnos.models.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter @Setter
public class MedicalAppointment extends Appointment{
	
	@ManyToOne	
	private Doctor doctor;
	
	@ManyToOne
	private Patient patient;
		
	public MedicalAppointment() {
		super();
	}
	public MedicalAppointment(Place place, Servicio service, LocalDate day, LocalTime hour,Doctor doctor,Patient patient) {
		super(place, service, day, hour);
		this.doctor=doctor;
		this.patient=patient;
	}
	
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	
	

}
