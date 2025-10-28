package com.testeando.turnos.models.entities;

import com.testeando.turnos.models.entities.Specialty;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Doctor extends Person {
	@ManyToOne
	@JoinColumn(name = "id_specialty")
	private Specialty specialty;
	
	private String licenseNumber;
	
	public Doctor() {
		super();
	}
	
	public Doctor(String lastname,String firstname,String email,int dni,Specialty specialty,String licenseNumber) {
		super(lastname,firstname,email,dni);
		this.specialty=specialty;
		this.licenseNumber=licenseNumber;
		
	}

	public Specialty getSpecialty() {
		return specialty;
	}

	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
	
	

	

}
