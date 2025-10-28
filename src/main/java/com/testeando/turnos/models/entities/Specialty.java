package com.testeando.turnos.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Specialty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSpecialty;
	private String name;
	
	public Specialty() {
	}
	
	public Specialty(int idSpecialty, String name) {
		super();
		this.idSpecialty = idSpecialty;
		this.name = name;
	}

	public int getIdSpecialty() {
		return idSpecialty;
	}

	public void setIdSpecialty(int idSpecialty) {
		this.idSpecialty = idSpecialty;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

}
