package com.testeando.turnos.models.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;


@MappedSuperclass
@Getter @Setter
public abstract class Appointment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected int idAppointment;
	
	@ManyToOne
	@JoinColumn(name="id_place") // nombre que realmente existe en la tabla
	protected Place place;
	
	@ManyToOne
	@JoinColumn(name="id_service") // nombre que realmente existe en la tabla
	private Servicio service;




	
	protected LocalDate day;
	protected LocalTime hour;
	
	
	
	public Appointment() {
		
	}

	public Appointment(Place place, Servicio service, LocalDate day, LocalTime hour) {
		this.place = place;
		this.service = service;
		this.day = day;
		this.hour= hour;
	}

	public int getIdAppointment() {
		return idAppointment;
	}

	public void setIdAppointment(int idAppointment) {
		this.idAppointment = idAppointment;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public Servicio getService() {
		return service;
	}

	public void setService(Servicio service) {
		this.service = service;
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
