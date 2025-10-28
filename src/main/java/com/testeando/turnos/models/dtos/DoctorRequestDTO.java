package com.testeando.turnos.models.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DoctorRequestDTO {
	private String firstName;
	private String lastName;
	private int dni;
	private String licenseNumber;
	private Integer idSpecialty;
	private String email; // ✅ AGREGALO ACÁ

	public DoctorRequestDTO() {}

	public DoctorRequestDTO(String firstName, String lastName, int dni, String licenseNumber, int idSpecialty, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dni = dni;
		this.licenseNumber = licenseNumber;
		this.idSpecialty = idSpecialty;
		this.email = email;
	}


}
