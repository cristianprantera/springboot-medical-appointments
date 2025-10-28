package com.testeando.turnos.models.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

public class MedicalAppointmentResponseDTO {
	private int id;
	
	private String doctorName;
	private String doctorSpecialty;
	
	private String patientName;
	private String patientDni;
	
	private String placeName;
	private String serviceName;
	
	private LocalDate day;
	private LocalTime hour;
	
	public MedicalAppointmentResponseDTO() {
	}

	public MedicalAppointmentResponseDTO(int id, String doctorName, String doctorSpecialty, String patientName,
			String patientDni, String placeName, String serviceName, LocalDate day, LocalTime hour) {
		super();
		this.id = id;
		this.doctorName = doctorName;
		this.doctorSpecialty = doctorSpecialty;
		this.patientName = patientName;
		this.patientDni = patientDni;
		this.placeName = placeName;
		this.serviceName = serviceName;
		this.day = day;
		this.hour = hour;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getDoctorSpecialty() {
		return doctorSpecialty;
	}

	public void setDoctorSpecialty(String doctorSpecialty) {
		this.doctorSpecialty = doctorSpecialty;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientDni() {
		return patientDni;
	}

	public void setPatientDni(String patientDni) {
		this.patientDni = patientDni;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public LocalDate getDay() {
		return day;
	}

	public void setDay(LocalDate day) {
		this.day = day;
	}

	public LocalTime getHour() {
		return hour;
	}

	public void setHour(LocalTime hour) {
		this.hour = hour;
	}

	

}
