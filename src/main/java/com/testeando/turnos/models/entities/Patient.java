package com.testeando.turnos.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Patient extends Person {
    @ManyToOne
    private ObraSocial obraSocial;

    public Patient() {
        super();
    }

    public Patient(String lastname, String firstname,String email, int dni, ObraSocial obraSocial) {
        super(lastname, firstname, email,dni);
        this.obraSocial = obraSocial;
    }

    // GETTERS MANUALES
    public ObraSocial getObraSocial() {
        return this.obraSocial;
    }
    
    public void setObraSocial(ObraSocial obraSocial) {
        this.obraSocial = obraSocial;
    }
}