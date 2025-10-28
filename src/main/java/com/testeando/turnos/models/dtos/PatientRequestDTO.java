package com.testeando.turnos.models.dtos;

public class PatientRequestDTO {
    private String lastName;
    private String firstName;
    private String email;
    private int dni;
    private int obraSocialId;

    // Constructor vacío
    public PatientRequestDTO() {}

    // Constructor completo
    public PatientRequestDTO(String lastname, String firstname,String email ,int dni, int obraSocialId) {
        this.lastName = lastname;
        this.firstName = firstname;
        this.email=email;
        this.dni = dni;
        this.obraSocialId = obraSocialId;
    }

    // Getters
    public String getLastname() {
        return lastName;
    }

    public String getFirstname() {
        return firstName;
    }

    public int getDni() {
        return dni;
    }

    public int getObraSocialId() {
        return obraSocialId;
    }

    // Setters
    public void setLastname(String lastname) {
        this.lastName = lastname;
    }

    public void setFirstname(String firstname) {
        this.firstName = firstname;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public void setObraSocialId(int obraSocialId) {
        this.obraSocialId = obraSocialId;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
    
    
}
