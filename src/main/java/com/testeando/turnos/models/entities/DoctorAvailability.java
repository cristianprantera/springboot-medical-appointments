package com.testeando.turnos.models.entities;

import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

import java.time.DayOfWeek;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class DoctorAvailability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id") // Para controlar el nombre de la columna
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Servicio servicio;
    
    @Enumerated(EnumType.STRING)
    private DayOfWeek day;

    private LocalTime startHour;
    private LocalTime finishHour;
    
	public DoctorAvailability() {
	}

	public DoctorAvailability(Doctor doctor, Servicio servicio, DayOfWeek day, LocalTime startHour,
			LocalTime finishHour) {
		this.doctor = doctor;
		this.servicio = servicio;
		this.day = day;
		this.startHour = startHour;
		this.finishHour = finishHour;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	public DayOfWeek getDay() {
		return day;
	}

	public void setDay(DayOfWeek day) {
		this.day = day;
	}

	public LocalTime getStartHour() {
		return startHour;
	}

	public void setStartHour(LocalTime startHour) {
		this.startHour = startHour;
	}

	public LocalTime getFinishHour() {
		return finishHour;
	}

	public void setFinishHour(LocalTime finishHour) {
		this.finishHour = finishHour;
	}
    
    
    
    
}
