package com.testeando.turnos.models.dtos;

public class DoctorResponseDTO {
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private int dni;
	private String licenseNumber;
	private String specialtyName;
	
	
	public DoctorResponseDTO() {
		
	}

	public DoctorResponseDTO(int id, String firstName, String lastName,String email,int dni, String licenseNumber,
			String specialtyName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email=email;
		this.dni = dni;
		this.licenseNumber = licenseNumber;
		this.specialtyName = specialtyName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String getSpecialtyName() {
		return specialtyName;
	}

	public void setSpecialtyName(String specialtyName) {
		this.specialtyName = specialtyName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
