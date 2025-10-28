package com.testeando.turnos.models.entities;

import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter

public class Servicio {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idService;
	
	private String name;
	private int durationService;
	
    @ManyToOne
    @JoinColumn(name="id_specialty")
    private Specialty specialty;
	
	public Servicio() {
	}

	public Servicio(String name,int durationService,Specialty specialty) {
		super();
		this.name = name;
		this.durationService=durationService;
		this.specialty=specialty;
	}


	public Specialty getSpecialty() {
		return specialty;
	}

	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}

	public Long getIdService() {
		return idService;
	}




	public void setIdService(Long idService) {
		this.idService = idService;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public int getDurationService() {
		return durationService;
	}




	public void setDurationService(int durationService) {
		this.durationService = durationService;
	}


	
	

	
	
	
	
}
