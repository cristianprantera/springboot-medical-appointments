package com.testeando.turnos.models.dtos;

public class PatientResponseDTO {
    private Long id;
    private int dni;
    private String firstName;
    private String lastName;  
    private String email;
    private String obraSocialName;

    public PatientResponseDTO() {}

    public PatientResponseDTO(Long id, int dni, String firstName, String lastName,String email, String obraSocialName) {
        this.id = id;
        this.dni = dni;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email=email;
        this.obraSocialName = obraSocialName;
    }

    // GETTERS CORREGIDOS (con "N" mayúscula)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getFirstName() {    // ← CORREGIDO: getFirstName()
        return firstName;
    }

    public void setFirstName(String firstName) {  // ← CORREGIDO: setFirstName()
        this.firstName = firstName;
    }

    public String getLastName() {     // ← CORREGIDO: getLastName()
        return lastName;
    }

    public void setLastName(String lastName) {    // ← CORREGIDO: setLastName()
        this.lastName = lastName;
    }

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getObraSocialName() {
        return obraSocialName;
    }

    public void setObraSocialName(String obraSocialName) {
        this.obraSocialName = obraSocialName;
    }
}