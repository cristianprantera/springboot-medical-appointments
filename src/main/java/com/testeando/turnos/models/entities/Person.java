package com.testeando.turnos.models.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Column;  // ← Importar esta anotación

@MappedSuperclass
public abstract class Person {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    
    @Column(name = "lastname")  
    protected String lastName;
    
    @Column(name = "firstname") 
    protected String firstName;
    
    protected String email;
    
    protected int dni;
    
    public Person() {}
    
    public Person(String lastName, String firstName,String email, int dni) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email=email;
        this.dni = dni;
    }
    
    // GETTERS MANUALES
    public int getId() {
        return this.id;
    }
    
    public String getLastName() {
        return this.lastName;
    }
    
    public String getFirstName() {
        return this.firstName;
    }
    
    public int getDni() {
        return this.dni;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setLastName(String lastName) {  
        this.lastName = lastName;
    }
    
    public void setFirstName(String firstName) { 
        this.firstName = firstName;
    }
    
    public void setDni(int dni) {
        this.dni = dni;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
    
}